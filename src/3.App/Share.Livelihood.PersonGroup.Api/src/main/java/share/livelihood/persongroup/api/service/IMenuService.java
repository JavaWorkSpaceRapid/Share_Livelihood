package share.livelihood.persongroup.api.service;

import share.livelihood.persongroup.domain.core.MenuInfoDomain;

import java.util.List;

public interface IMenuService
{
    String addMenuInfo(MenuInfoDomain menuInfoDomain);

    Boolean updateMenuInfo(MenuInfoDomain menuInfoDomain);

    Boolean deleteMenuInfo(String menuId);

    List<MenuInfoDomain> getMenuList();
}
