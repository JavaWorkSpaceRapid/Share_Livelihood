package share.livelihood.persongroup.api.service;


import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;

import java.util.List;

/**
 * Created by SunDawei on 2019/6/13.
 */
public interface IAccountService
{
    Boolean register(AccountInfoDomain accountInfoDomain);

    String addPersonalInfo(PersonalInformation personalInformation);

    void editPersonalInfo(PersonalInformation personalInformation);

    PersonalInformation getPersonalInfoByUserId(String userId);

    AccountInfoDomain login(String userName, String userPwd, String type);

    Boolean smsVerification(String phoneNo);

    AccountInfoDomain getAccountInfoByUserId(String userId);

    Boolean setAuthority(String userId, String roleId, List<String> menuIdList);
}
