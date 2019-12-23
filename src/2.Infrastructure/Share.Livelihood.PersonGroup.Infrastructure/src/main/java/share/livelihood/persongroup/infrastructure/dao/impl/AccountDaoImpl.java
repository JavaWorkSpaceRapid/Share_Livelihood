package share.livelihood.persongroup.infrastructure.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;
import share.livelihood.persongroup.infrastructure.dao.IAccountDao;
import share.livelihood.persongroup.infrastructure.mapper.IAccountMapper;
import share.livelihood.persongroup.infrastructure.mapper.IPersonalInfoMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SunDawei on 2019/6/13.
 */
@Component
public class AccountDaoImpl implements IAccountDao
{
    @Autowired
    private IAccountMapper iAccountMapper;

    @Autowired
    private IPersonalInfoMapper iPersonalInfoMapper;
    /**
     * 注册新用户
     * @param accountInfoDomain
     * @return
     */
    @Override
    public Boolean addAccount(AccountInfoDomain accountInfoDomain)
    {
        iAccountMapper.addAccount(accountInfoDomain);
        return true;
    }

    @Override
    public AccountInfoDomain getAccountInfoByOpenId(String openId)
    {
        return iAccountMapper.getAccountInfoByOpenId(openId);
    }

    @Override
    public PersonalInformation getPersonalInfoByOpenIdPhone(String openId, String phone)
    {
        Map<String,String> map =new HashMap<>();
        map.put("openId",openId);
        map.put("phone",phone);
        return iPersonalInfoMapper.getPersonalInfoByOpenIdPhone(map);
    }

    @Override
    public PersonalInformation getPersonalInfoByOpenId(String openId)
    {
        return iPersonalInfoMapper.getPersonalInfoByOpenId(openId);
    }

    @Override
    public void editPersonalInfoVerifyCode(PersonalInformation personalInformation)
    {
        iPersonalInfoMapper.editPersonalInfoVerifyCode(personalInformation);
    }

    @Override
    public void editPersonalInfoUserId(PersonalInformation personalInformation)
    {
        iPersonalInfoMapper.editPersonalInfoUserId(personalInformation);
    }

    @Override
    public Boolean editAccountInfo(AccountInfoDomain accountInfoDomain)
    {
        iAccountMapper.editAccountInfo(accountInfoDomain);
        return true;
    }

    @Override
    public String addPersonalInfo(PersonalInformation personalInformation)
    {
        iPersonalInfoMapper.addPersonalInfo(personalInformation);
        return personalInformation.getPersonal_id();
    }

    @Override
    public void editPersonalInfo(PersonalInformation personalInformation)
    {
        iPersonalInfoMapper.editPersonalInfo(personalInformation);
    }

    @Override
    public PersonalInformation getPersonalInfoByUserId(String userId)
    {
        return iPersonalInfoMapper.getPersonalInfoByUserId(userId);
    }

    @Override
    public AccountInfoDomain getAccountByNamePwd(String userName, String userPwd)
    {
        AccountInfoDomain accountInfoDomain = new AccountInfoDomain();
        accountInfoDomain.setUser_name(userName);
        accountInfoDomain.setUser_pwd(userPwd);
        return iAccountMapper.getAccountByNamePwd(accountInfoDomain);
    }

    @Override
    public AccountInfoDomain getAccountByNamePwdType(String userName, String userPwd)
    {
        AccountInfoDomain accountInfoDomain = new AccountInfoDomain();
        accountInfoDomain.setUser_name(userName);
        accountInfoDomain.setUser_pwd(userPwd);
        return iAccountMapper.getAccountByNamePwdType(accountInfoDomain);
    }

    @Override
    public AccountInfoDomain getAccountInfoByUserId(String userId)
    {
        return iAccountMapper.getAccountInfoByUserId(userId);
    }

    @Override
    public AccountInfoDomain getAccountInfoByUserName(String userName)
    {
        return iAccountMapper.getAccountInfoByUserName(userName);
    }

    @Override
    public Boolean editAccountVerifyCode(AccountInfoDomain accountInfoDomain)
    {
        iAccountMapper.editAccountVerifyCode(accountInfoDomain);
        return true;
    }
}
