package share.livelihood.persongroup.domain.core;

import lombok.Data;
import share.domain.EntityBase;

import java.util.Date;

/**
 * Created by SunDawei on 2019/6/13.
 * 账户信息类
 */
@Data
public class AccountInfoDomain extends EntityBase
{
    /**
     * 账户唯一ID
     */
    private String user_id;

    /**
     * 账户名称
     */
    private String user_name;

    /**
     * 账户密码
     */
    private String user_pwd;

    /**
     * 头像
     */
    private String user_img;

    /**
     * 验证码
     */
    private String verify_code;

    /**
     * 登录类型:0（账户登录），1（手机号登录）
     */
    private String login_type;

    /**
     * 验证码失效控制时间
     */
    private Date verify_time;

    /**
     * 用户状态：0（正常），1（注销），2（删除）
     */
    private int status;

    private String open_id;

    /**
     * 用户个人信息
     */
    private PersonalInformation personalInformation;
}
