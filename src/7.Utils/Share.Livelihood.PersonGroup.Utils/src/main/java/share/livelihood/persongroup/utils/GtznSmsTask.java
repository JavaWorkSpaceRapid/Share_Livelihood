package share.livelihood.persongroup.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.Random;

/**
 * 国通智能短信通道
 */
@Component
public class GtznSmsTask
{

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GtznSmsTask.class);

    /**
     * 短信发送方法
     *
     * @param phoneNo
     * @throws Exception
     * @author SunDawei
     * @date 2019-11-30
     */
    public void execute(String phoneNo,String verifyCode) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
        sb.append("&to").append("=").append(phoneNo);
        sb.append("&param").append("=").append(URLEncoder.encode("","UTF-8"));
		sb.append("&smsContent").append("=").append( URLEncoder.encode("【酌源购】您的验证码为"+verifyCode+"，该验证码5分钟内有效，请勿泄漏与他人。","UTF-8"));
        String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
        String result = HttpUtil.post(Config.BASE_URL, body);
        System.out.println(result);

    }

    /**
     * 生成短信验证码
     * @return String
     * @author SunDawei
     * @date 2019-11-30
     */
    public String verifyCode()
    {
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        return verifyCode;
    }
}
