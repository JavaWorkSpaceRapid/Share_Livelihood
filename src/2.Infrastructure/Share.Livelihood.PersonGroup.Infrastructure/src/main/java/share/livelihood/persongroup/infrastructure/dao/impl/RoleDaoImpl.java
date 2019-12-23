package share.livelihood.persongroup.infrastructure.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import share.livelihood.persongroup.domain.core.RoleInfoDomain;
import share.livelihood.persongroup.domain.core.RoleMenuAuthDomain;
import share.livelihood.persongroup.infrastructure.dao.IRoleDao;
import share.livelihood.persongroup.infrastructure.mapper.IRoleMapper;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 22:16
 */
@Component
public class RoleDaoImpl implements IRoleDao
{
    @Autowired
    private IRoleMapper iRoleMapper;

    @Override
    public String addRoleInfo(RoleInfoDomain roleInfoDomain)
    {
        return String.valueOf(iRoleMapper.addRoleInfo(roleInfoDomain));
    }

    @Override
    public String addRoleMenuInfo(RoleMenuAuthDomain roleMenuAuthDomain)
    {
        return String.valueOf(iRoleMapper.addRoleMenuInfo(roleMenuAuthDomain));
    }

    @Override
    public List<RoleMenuAuthDomain> getListRoleMenuInfo(String roleId)
    {
        return iRoleMapper.getListRoleMenuInfo(roleId);
    }

    @Override
    public List<RoleInfoDomain> getListRoleInfo()
    {
        return iRoleMapper.getListRoleInfo();
    }
}
