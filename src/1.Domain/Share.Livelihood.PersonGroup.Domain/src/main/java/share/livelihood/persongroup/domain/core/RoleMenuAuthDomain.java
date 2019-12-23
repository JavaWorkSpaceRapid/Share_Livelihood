package share.livelihood.persongroup.domain.core;

import lombok.Data;
import share.domain.EntityBase;

/**
 * 角色菜单权限类
 *
 * @author SunDawei
 * @date 2019/10/31 22:03
 */
@Data
public class RoleMenuAuthDomain extends EntityBase
{
    private String role_auth_id;
    private String role_id;
    private String menu_id;
}