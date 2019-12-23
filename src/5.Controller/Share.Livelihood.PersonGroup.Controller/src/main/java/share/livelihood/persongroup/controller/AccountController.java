package share.livelihood.persongroup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.persongroup.api.service.IAccountService;
import share.livelihood.persongroup.api.service.ISendSmsService;
import share.livelihood.persongroup.api.service.IWeiXinLoginService;
import share.livelihood.persongroup.domain.core.AccountInfoDomain;
import share.livelihood.persongroup.domain.core.PersonalInformation;
import share.web.ApiController;

import java.util.List;
import java.util.Map;

/**
 * Created by SunDawei on 2019/6/13.
 * 账户操作Controller
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Account")
@Api(value = "/livelihood/Account", description = "账户操作")
public class AccountController extends ApiController
{
    @Autowired
    private IAccountService iAccountService;

    @Autowired
    private IWeiXinLoginService iWeiXinLoginService;

    @Autowired
    private ISendSmsService iSendSmsService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户", notes = "新增用户")
    @NoAuth
    public LiveResult<Boolean> register(@RequestBody AccountInfoDomain accountInfoDomain)
    {
        return ok(iAccountService.register(accountInfoDomain));
    }

    @RequestMapping(value = "/weiChatLogin", method = RequestMethod.POST)
    @ApiOperation(value = "微信登录", notes = "微信登录")
    @NoAuth
    public LiveResult<Map<String,Object>> weiChatLogin(@RequestParam(name = "code",required = false) String code,
                                                       @RequestParam(name = "signature",required = false) String signature,
                                                       @RequestParam(name = "encryptedData",required = false) String encryptedData,
                                                       @RequestParam(name = "iv",required = false) String iv,
                                                       @RequestBody PersonalInformation personalInformation)
    {
        return ok(iWeiXinLoginService.checkLogin(code,personalInformation));
    }

    @RequestMapping(value = "/bindMobile", method = RequestMethod.POST)
    @ApiOperation(value = "绑定手机号", notes = "绑定手机号")
    @NoAuth
    public LiveResult<AccountInfoDomain> bindMobile(@RequestParam(name = "openId") String openId,
                                                    @RequestParam(name = "mobile") String mobile,
                                                    @RequestParam(name = "verify_code") String verify_code)
    {
        return ok(iWeiXinLoginService.bindMobile(openId,mobile,verify_code));
    }

    @RequestMapping(value = "/addPersonalInfo", method = RequestMethod.POST)
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息")
    @NoAuth
    public LiveResult<String> addPersonalInfo(@RequestBody PersonalInformation personalInformation)
    {
        return ok(iAccountService.addPersonalInfo(personalInformation));
    }

    @RequestMapping(value = "/editPersonalInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @NoAuth
    public LiveResult editPersonalInfo(@RequestBody PersonalInformation personalInformation)
    {
        iAccountService.editPersonalInfo(personalInformation);
        return ok();
    }

    @RequestMapping(value = "/getPersonalInfoByUserId", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @NoAuth
    public LiveResult<PersonalInformation> getPersonalInfoByUserId(@RequestParam(name = "userId") String userId)
    {
        return ok(iAccountService.getPersonalInfoByUserId(userId));
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录账号", notes = "登录账号。账号登录type为0，手机登陆type为1。")
    @NoAuth
    public LiveResult<AccountInfoDomain> login(@RequestParam(name = "userName", required = true) String userName,
                                               @RequestParam(name = "userPwd", required = true) String userPwd,
                                               @RequestParam(name = "type", defaultValue = "0", required = true) String type)
    {
        return ok(iAccountService.login(userName, userPwd, type));
    }

    @RequestMapping(value = "/smsVerification", method = RequestMethod.GET)
    @ApiOperation(value = "手机短信验证", notes = "手机短信验证")
    @NoAuth
    public LiveResult<Boolean> smsVerification(@RequestParam(name = "phoneNo") String phoneNo)
    {
        return ok(iAccountService.smsVerification(phoneNo));
    }

    @RequestMapping(value = "/smsWeChatVerification", method = RequestMethod.GET)
    @ApiOperation(value = "手机短信验证", notes = "手机短信验证")
    @NoAuth
    public LiveResult<Boolean> smsWeChatVerification(@RequestParam(name = "phoneNo") String phoneNo,
                                                     @RequestParam(name = "openId") String openId)
    {
        iSendSmsService.sendWeiChatSms(openId,phoneNo);
        return ok(true);
    }

    @RequestMapping(value = "/getAccountInfoByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "获取账户信息", notes = "获取账户信息")
    @NoAuth
    public LiveResult<AccountInfoDomain> getAccountInfoByUserId(@RequestParam(name = "userId", required = true) String userId)
    {
        return ok(iAccountService.getAccountInfoByUserId(userId));
    }

    @RequestMapping(value = "/setAuthority", method = RequestMethod.POST)
    @ApiOperation(value = "绑定权限", notes = "绑定权限")
    @NoAuth
    public LiveResult<Boolean> setAuthority(@RequestParam(name = "userId") String userId,
                                            @RequestParam(name = "roleId") String roleId,
                                            @RequestBody List<String> menuIdList)
    {
        return ok(iAccountService.setAuthority(userId, roleId, menuIdList));
    }

}
