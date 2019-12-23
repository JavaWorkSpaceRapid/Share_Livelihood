package share.livelihood.persongroup.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.base.annotation.InitAuth;
import share.base.exceptions.BaseException;
import share.base.extensions.ObjectExtension;
import share.livelihood.persongroup.api.service.IAccountService;
import share.livelihood.persongroup.api.service.ISendSmsService;
import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;
import share.livelihood.persongroup.infrastructure.dao.IAccountDao;
import share.livelihood.persongroup.utils.ToolUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by SunDawei on 2019/6/13.
 */
@Service
public class AccountServiceImpl implements IAccountService
{

    @Autowired
    private IAccountDao iAccountDao;

    @Autowired
    private ISendSmsService iSendSmsService;

    /**
     * 注册新用户信息
     *
     * @param accountInfoDomain
     * @return Boolean
     * @author SunDawei
     * @date 2019-06-10
     */
    @Override
    public Boolean register(AccountInfoDomain accountInfoDomain)
    {
        if (null != accountInfoDomain
                && null != accountInfoDomain.getUser_name()
                && null != accountInfoDomain.getUser_pwd())
        {
            AccountInfoDomain accountInfoDomain1 = iAccountDao.getAccountInfoByUserName(accountInfoDomain.getUser_name());
            if(ObjectExtension.isNotNull(accountInfoDomain1))
            {
                throw new BaseException("此账号已存在！");
            }
            //根据用户名称和密码，获取账号信息
            AccountInfoDomain accountInfoDomainPre = iAccountDao.getAccountByNamePwd(accountInfoDomain.getUser_name(), accountInfoDomain.getUser_pwd());
            if (null == accountInfoDomainPre)
            {
                return iAccountDao.addAccount(accountInfoDomain);
            } else
            {
                return false;
            }
        }
        return false;
    }

    @Override
    public String addPersonalInfo(PersonalInformation personalInformation)
    {
        return iAccountDao.addPersonalInfo(personalInformation);
    }

    @Override
    public void editPersonalInfo(PersonalInformation personalInformation)
    {
        iAccountDao.editPersonalInfo(personalInformation);
    }

    @Override
    public PersonalInformation getPersonalInfoByUserId(String userId)
    {
        return iAccountDao.getPersonalInfoByUserId(userId);
    }

    /**
     * 登录账号
     *
     * @param userName
     * @param userPwd
     * @return Boolean
     * @author SunDawei
     * @date 2019-06-10
     */
    @Override
    @InitAuth
    public AccountInfoDomain login(String userName, String userPwd, String type)
    {
        if(type.equals("1"))
        {
            //根据用户名称和密码，获取账号信息
            AccountInfoDomain accountInfoDomain = iAccountDao.getAccountByNamePwdType(userName, userPwd);
            if(ObjectExtension.isNotNull(accountInfoDomain))
            {
                if(ToolUtil.timeDifference(accountInfoDomain.getVerify_time(),new Date()) > 300)
                {
                    throw new BaseException("验证码已失效！");
                }
                else
                {
                    return accountInfoDomain;
                }
            }
            else
            {
                throw new BaseException("亲！用户名或验证码错误！！！");
            }
        }
        else
        {
            AccountInfoDomain accountInfoDomain = iAccountDao.getAccountByNamePwd(userName, userPwd);
            if (null != accountInfoDomain)
            {
                return accountInfoDomain;
            }
            throw new BaseException("亲！用户名或密码错误！！！");
        }
    }

    @Override
    public Boolean smsVerification(String phoneNo)
    {
        iSendSmsService.sendSms(phoneNo);
        return true;
    }

    @Override
    public AccountInfoDomain getAccountInfoByUserId(String userId)
    {
        return iAccountDao.getAccountInfoByUserId(userId);
    }

    @Override
    public Boolean setAuthority(String userId, String roleId, List<String> menuIdList)
    {
        return null;
    }
}
