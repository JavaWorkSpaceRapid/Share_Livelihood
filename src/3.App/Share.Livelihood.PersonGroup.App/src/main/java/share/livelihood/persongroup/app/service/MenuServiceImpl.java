package share.livelihood.persongroup.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.livelihood.persongroup.api.service.IMenuService;
import share.livelihood.persongroup.domain.core.MenuInfoDomain;
import share.livelihood.persongroup.infrastructure.dao.IMenuDao;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 21:27
 */
@Service
public class MenuServiceImpl implements IMenuService
{
    @Autowired
    private IMenuDao iMenuDao;

    @Override
    public String addMenuInfo(MenuInfoDomain menuInfoDomain)
    {
        return iMenuDao.addMenuInfo(menuInfoDomain);
    }

    @Override
    public Boolean updateMenuInfo(MenuInfoDomain menuInfoDomain)
    {
        iMenuDao.updateMenuInfo(menuInfoDomain);
        return true;
    }

    @Override
    public Boolean deleteMenuInfo(String menuId)
    {
        iMenuDao.deleteMenuInfo(menuId);
        return true;
    }

    @Override
    public List<MenuInfoDomain> getMenuList()
    {
        return iMenuDao.getMenuList();
    }
}
