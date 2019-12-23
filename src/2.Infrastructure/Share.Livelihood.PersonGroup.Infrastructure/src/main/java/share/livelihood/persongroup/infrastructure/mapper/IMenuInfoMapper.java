package share.livelihood.persongroup.infrastructure.mapper;

import share.livelihood.persongroup.domain.core.MenuInfoDomain;

import java.util.List;

public interface IMenuInfoMapper
{
    Integer addMenuInfo(MenuInfoDomain menuInfoDomain);

    void updateMenuInfo(MenuInfoDomain menuInfoDomain);

    void deleteMenuInfo(String menuId);

    List<MenuInfoDomain> getMenuList();
}
