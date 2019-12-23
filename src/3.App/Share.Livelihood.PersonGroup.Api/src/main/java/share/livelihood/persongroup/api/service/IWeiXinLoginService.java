package share.livelihood.persongroup.api.service;

import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;

import java.util.Map;

public interface IWeiXinLoginService
{
    Map<String,Object> checkLogin(String code, PersonalInformation personalInformation);

    AccountInfoDomain bindMobile(String openId, String mobile, String verify_code);
}
