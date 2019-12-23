package share.livelihood.persongroup.infrastructure.dao.impl;

import livelihood.login.domain.core.MenuInfoDomain;
import livelihood.login.domain.dao.IMenuDao;
import livelihood.login.infrastructure.mapper.IMenuInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 21:19
 */
@Component
public class MenuDaoImpl implements IMenuDao
{
    @Autowired
    private IMenuInfoMapper iMenuInfoMapper;

    @Override
    public String addMenuInfo(MenuInfoDomain menuInfoDomain)
    {
        return String.valueOf(iMenuInfoMapper.addMenuInfo(menuInfoDomain));
    }

    @Override
    public void updateMenuInfo(MenuInfoDomain menuInfoDomain)
    {
        iMenuInfoMapper.updateMenuInfo(menuInfoDomain);
    }

    @Override
    public void deleteMenuInfo(String menuId)
    {
        iMenuInfoMapper.deleteMenuInfo(menuId);
    }

    @Override
    public List<MenuInfoDomain> getMenuList()
    {
        return iMenuInfoMapper.getMenuList();
    }
}
