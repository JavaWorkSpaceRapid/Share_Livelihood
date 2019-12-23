package share.livelihood.persongroup.domain.core;

import lombok.Data;
import share.domain.EntityBase;

import java.util.Date;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/12/4 20:33
 */
@Data
public class PersonalInformation extends EntityBase
{
    /**
     * 唯一标识
     */
    private String personal_id;

    /**
     * 用户Id
     */
    private String user_id;
    /**
     * 用户性别
     */
    private String user_sex;
    /**
     * 用户姓名
     */
    private String user_name;
    /**
     * 用户生日
     */
    private String user_birthday;
    /**
     * 用户手机
     */
    private String user_mobile;

    private String open_id;
    private String nick_name;
    private String head_photo;
    private String city;
    private String province;
    private String country;

    private String verify_code;

    /**
     * 验证码失效控制时间
     */
    private Date verify_time;
}
