package share.livelihood.persongroup.infrastructure.mapper;

import share.livelihood.persongroup.domain.core.RoleInfoDomain;
import share.livelihood.persongroup.domain.core.RoleMenuAuthDomain;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 22:16
 */
public interface IRoleMapper
{
    Integer addRoleInfo(RoleInfoDomain roleInfoDomain);

    Integer addRoleMenuInfo(RoleMenuAuthDomain roleMenuAuthDomain);

    List<RoleMenuAuthDomain> getListRoleMenuInfo(String roleId);

    List<RoleInfoDomain> getListRoleInfo();
}
