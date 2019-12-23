package share.livelihood.persongroup.infrastructure.mapper;

import share.livelihood.persongroup.domain.core.AccountInfoDomain;

import java.util.Map;

/**
 * Created by SunDawei on 2019/6/13.
 */
public interface IAccountMapper
{
    void addAccount(AccountInfoDomain accountInfoDomain);

    void editAccountInfo(AccountInfoDomain accountInfoDomain);

    AccountInfoDomain getAccountInfoByOpenId(String openId);

    AccountInfoDomain getAccountByNamePwd(AccountInfoDomain accountInfoDomain);

    AccountInfoDomain getAccountByNamePwdType(AccountInfoDomain accountInfoDomain);

    AccountInfoDomain getAccountInfoByUserId(String userId);

    AccountInfoDomain getAccountInfoByUserName(String userName);

    void editAccountVerifyCode(AccountInfoDomain accountInfoDomain);
}
