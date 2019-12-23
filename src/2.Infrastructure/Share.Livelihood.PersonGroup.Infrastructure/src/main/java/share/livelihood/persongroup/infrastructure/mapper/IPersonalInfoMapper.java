package share.livelihood.persongroup.infrastructure.mapper;

import share.livelihood.persongroup.domain.core.PersonalInformation;

import java.util.Map;

/**
 * Created by SunDawei on 2019/12/4.
 */
public interface IPersonalInfoMapper
{
    Integer addPersonalInfo(PersonalInformation personalInformation);

    void editPersonalInfo(PersonalInformation personalInformation);

    void editPersonalInfoVerifyCode(PersonalInformation personalInformation);

    void editPersonalInfoUserId(PersonalInformation personalInformation);

    PersonalInformation getPersonalInfoByUserId(String userId);

    PersonalInformation getPersonalInfoByOpenIdPhone(Map<String, String> map);

    PersonalInformation getPersonalInfoByOpenId(String openId);
}
