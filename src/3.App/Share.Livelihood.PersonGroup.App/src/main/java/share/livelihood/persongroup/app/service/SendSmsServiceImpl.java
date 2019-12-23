package share.livelihood.persongroup.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.base.extensions.ObjectExtension;
import share.livelihood.persongroup.api.service.ISendSmsService;
import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;
import share.livelihood.persongroup.infrastructure.dao.IAccountDao;
import share.livelihood.persongroup.utils.GtznSmsTask;

/**
 * Created by Administrator on 2019/4/24.
 */
@Service
public class SendSmsServiceImpl implements ISendSmsService
{
    @Autowired
    private GtznSmsTask gtznSmsTask;

    @Autowired
    private IAccountDao iAccountDao;

    @Override
    public void sendSms(String phoneNo)
    {
        try
        {
            AccountInfoDomain accountInfoDomain = iAccountDao.getAccountInfoByUserName(phoneNo);
            if(ObjectExtension.isNotNull(accountInfoDomain))
            {
                accountInfoDomain.setVerify_code(gtznSmsTask.verifyCode());
                iAccountDao.editAccountVerifyCode(accountInfoDomain);
            }
            else
            {
                accountInfoDomain = new AccountInfoDomain();
                accountInfoDomain.setLogin_type("1");
                accountInfoDomain.setUser_name(phoneNo);
                accountInfoDomain.setVerify_code(gtznSmsTask.verifyCode());
                iAccountDao.addAccount(accountInfoDomain);
            }
            gtznSmsTask.execute(phoneNo,accountInfoDomain.getVerify_code());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWeiChatSms(String openId, String phoneNo)
    {
        try
        {
            PersonalInformation personalInformation = iAccountDao.getPersonalInfoByOpenIdPhone(openId,phoneNo);
            if(ObjectExtension.isNotNull(personalInformation))
            {
                personalInformation.setVerify_code(gtznSmsTask.verifyCode());
                iAccountDao.editPersonalInfoVerifyCode(personalInformation);
            }
            else
            {
                personalInformation = new PersonalInformation();
                personalInformation.setUser_mobile(phoneNo);
                personalInformation.setVerify_code(gtznSmsTask.verifyCode());
                personalInformation.setOpen_id(openId);
                iAccountDao.addPersonalInfo(personalInformation);
            }
            gtznSmsTask.execute(phoneNo,personalInformation.getVerify_code());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
