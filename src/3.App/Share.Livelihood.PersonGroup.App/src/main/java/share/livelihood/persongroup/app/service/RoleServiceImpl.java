package share.livelihood.persongroup.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.livelihood.persongroup.api.service.IRoleService;
import share.livelihood.persongroup.domain.core.RoleInfoDomain;
import share.livelihood.persongroup.domain.core.RoleMenuAuthDomain;
import share.livelihood.persongroup.infrastructure.dao.IRoleDao;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 22:15
 */
@Service
public class RoleServiceImpl implements IRoleService
{
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public String addRoleInfo(RoleInfoDomain roleInfoDomain)
    {
        return iRoleDao.addRoleInfo(roleInfoDomain);
    }

    @Override
    public String addRoleMenuInfo(RoleMenuAuthDomain roleMenuAuthDomain)
    {
        return iRoleDao.addRoleMenuInfo(roleMenuAuthDomain);
    }

    @Override
    public List<RoleMenuAuthDomain> getListRoleMenuInfo(String roleId)
    {
        return iRoleDao.getListRoleMenuInfo(roleId);
    }

    @Override
    public List<RoleInfoDomain> getListRoleInfo()
    {
        return iRoleDao.getListRoleInfo();
    }
}
