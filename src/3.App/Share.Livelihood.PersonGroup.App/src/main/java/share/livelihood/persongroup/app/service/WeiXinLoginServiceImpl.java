package share.livelihood.persongroup.app.service;

import lycan.base.exceptions.BaseException;
import lycan.base.extensions.ObjectExtension;
import lycan.base.extensions.StringExtension;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import share.livelihood.persongroup.api.service.IWeiXinLoginService;
import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;
import share.livelihood.persongroup.infrastructure.dao.IAccountDao;
import share.livelihood.persongroup.utils.GtznSmsTask;
import share.livelihood.persongroup.utils.ToolUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/12/14 14:28
 */
@Service
public class WeiXinLoginServiceImpl implements IWeiXinLoginService
{
    private Logger logger = LoggerFactory.getLogger(WeiXinLoginServiceImpl.class);

    // 获取access_token url
    private  String WX_AUTH_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Value("${publicvariable.WX_APP_ID}")
    private String WX_APP_ID;// appId

    @Value("${publicvariable.WX_APP_KEY}")
    private String WX_APP_KEY;// AppSecret

    @Autowired
    private IAccountDao iAccountDao;

    @Autowired
    private GtznSmsTask gtznSmsTask;

    public Map<String, Object> checkLogin(String code, PersonalInformation personalInformation)
    {
        try {
            Map<String, Object> outputMap = new HashMap<String, Object>();
            if (StringExtension.isNullOrWhiteSpace(code))
            {
                outputMap.put("success", false);
                outputMap.put("msg", "必要参数错误");
                return outputMap;
            }
            StringBuffer loginUrl = new StringBuffer();
            loginUrl.append(WX_AUTH_LOGIN_URL).append("?appid=")
                    .append(WX_APP_ID).append("&secret=")
                    .append(WX_APP_KEY).append("&js_code=").append(code)
                    .append("&grant_type=authorization_code");
            System.out.println("-----带有CODE--的URL---" + loginUrl.toString());
            String loginRet = this.httpRequest(loginUrl.toString(), "GET", null);
            System.out.println("返回信息---------" + loginRet);
            JSONObject grantObj = new JSONObject(loginRet);
            String errcode = grantObj.optString("errcode");
            if (StringUtils.isNotEmpty(errcode))
            {
                logger.error("------微信登录错误------" + loginRet);
                outputMap.put("success", false);
                outputMap.put("msg", "登录失败");
                throw new BaseException(loginRet);
            }
            String openId = grantObj.optString("openid");
            if (StringUtils.isEmpty(openId))
            {
                logger.error("------微信登录获取OpenId错误------" + loginRet);
                outputMap.put("success", false);
                outputMap.put("msg", "登录失败");
                throw new BaseException(loginRet);
            }
            if(null != personalInformation)
            {
                if(!StringExtension.isNullOrWhiteSpace(personalInformation.getOpen_id()))
                {
                    PersonalInformation personalInformation1 = iAccountDao.getPersonalInfoByOpenId(personalInformation.getOpen_id());
                    if(null != personalInformation1
                            && !StringExtension.isNullOrWhiteSpace(personalInformation1.getUser_id()))
                    {
                        AccountInfoDomain accountInfoDomain= iAccountDao.getAccountInfoByUserId(personalInformation1.getUser_id());
                        outputMap.put("openId", openId);
                        outputMap.put("accountInfoDomain",accountInfoDomain);
                        outputMap.put("success", true);
                        outputMap.put("code", "2000");
                        outputMap.put("msg", "成功");
                        return outputMap;
                    }
                }
                personalInformation.setOpen_id(openId);
                iAccountDao.addPersonalInfo(personalInformation);
            }
            outputMap.put("openId", openId);
            outputMap.put("personalInformation",personalInformation);
            outputMap.put("success", true);
            outputMap.put("code", "2001");
            outputMap.put("msg", "成功");
            return outputMap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BaseException(e.getMessage());
        }
    }

    @Override
    public AccountInfoDomain bindMobile(String openId, String mobile, String verify_code)
    {
        AccountInfoDomain accountInfoDomain = iAccountDao.getAccountInfoByUserName(mobile);
        PersonalInformation personalInformation = iAccountDao.getPersonalInfoByOpenIdPhone(openId, mobile);
        if(null != accountInfoDomain)
        {
            if (ObjectExtension.isNotNull(personalInformation))
            {
                if (ToolUtil.timeDifference(personalInformation.getVerify_time(), new Date()) > 300)
                {
                    throw new BaseException("验证码已失效！");
                }
                iAccountDao.editPersonalInfoUserId(personalInformation);
            }
            return accountInfoDomain;
        }
        else
        {
            accountInfoDomain = new AccountInfoDomain();
            accountInfoDomain.setUser_name(mobile);
            accountInfoDomain.setLogin_type("1");
            iAccountDao.addAccount(accountInfoDomain);
            iAccountDao.editPersonalInfoUserId(personalInformation);
            accountInfoDomain = iAccountDao.getAccountInfoByUserName(mobile);
            return accountInfoDomain;
        }
    }

    //处理http请求  requestUrl为请求地址  requestMethod请求方式，值为"GET"或"POST"
    public  String httpRequest(String requestUrl,String requestMethod,String outputStr)
    {
        StringBuffer buffer=null;
        try
        {
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //往服务器端写内容 也就是发起http请求需要带的参数
            if(null!=outputStr)
            {
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null)
            {
                buffer.append(line);
            }
            logger.info("[weixin]: do get request({}), and get response({}).", url, buffer.toString());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
