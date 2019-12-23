package share.livelihood.persongroup.infrastructure.dao;


import share.livelihood.persongroup.domain.core.MenuInfoDomain;

import java.util.List;

public interface IMenuDao
{

    String addMenuInfo(MenuInfoDomain menuInfoDomain);

    void updateMenuInfo(MenuInfoDomain menuInfoDomain);

    void deleteMenuInfo(String menuId);

    List<MenuInfoDomain> getMenuList();
}
