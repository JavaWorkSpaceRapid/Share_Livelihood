package share.livelihood.persongroup.infrastructure.dao;

import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;

/**
 * Created by SunDawei on 2019/6/13.
 */
public interface IAccountDao
{
    Boolean addAccount(AccountInfoDomain accountInfoDto);

    AccountInfoDomain getAccountInfoByOpenId(String openId);

    PersonalInformation getPersonalInfoByOpenIdPhone(String openId, String phone);

    PersonalInformation getPersonalInfoByOpenId(String openId);

    void editPersonalInfoVerifyCode(PersonalInformation personalInformation);

    void editPersonalInfoUserId(PersonalInformation personalInformation);

    Boolean editAccountInfo(AccountInfoDomain accountInfoDomain);

    String addPersonalInfo(PersonalInformation personalInformation);

    void editPersonalInfo(PersonalInformation personalInformation);

    PersonalInformation getPersonalInfoByUserId(String userId);

    AccountInfoDomain getAccountByNamePwd(String userName, String userPwd);

    AccountInfoDomain getAccountByNamePwdType(String userName, String userPwd);

    AccountInfoDomain getAccountInfoByUserId(String userId);

    AccountInfoDomain getAccountInfoByUserName(String userName);

    Boolean editAccountVerifyCode(AccountInfoDomain accountInfoDomain);
}
