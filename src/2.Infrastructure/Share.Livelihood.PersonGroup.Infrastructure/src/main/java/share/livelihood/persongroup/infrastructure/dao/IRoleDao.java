package share.livelihood.persongroup.infrastructure.dao;


import share.livelihood.persongroup.domain.core.RoleInfoDomain;
import share.livelihood.persongroup.domain.core.RoleMenuAuthDomain;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 22:15
 */
public interface IRoleDao
{
    String addRoleInfo(RoleInfoDomain roleInfoDomain);

    String addRoleMenuInfo(RoleMenuAuthDomain roleMenuAuthDomain);

    List<RoleMenuAuthDomain> getListRoleMenuInfo(String roleId);

    List<RoleInfoDomain> getListRoleInfo();
}
