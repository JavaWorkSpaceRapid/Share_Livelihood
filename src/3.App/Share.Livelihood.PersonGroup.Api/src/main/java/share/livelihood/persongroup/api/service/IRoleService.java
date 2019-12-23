package share.livelihood.persongroup.api.service;

import share.livelihood.persongroup.domain.core.RoleInfoDomain;
import share.livelihood.persongroup.domain.core.RoleMenuAuthDomain;

import java.util.List;

public interface IRoleService
{
    String addRoleInfo(RoleInfoDomain roleInfoDomain);

    String addRoleMenuInfo(RoleMenuAuthDomain roleMenuAuthDomain);

    List<RoleMenuAuthDomain> getListRoleMenuInfo(String roleId);

    List<RoleInfoDomain> getListRoleInfo();
}
