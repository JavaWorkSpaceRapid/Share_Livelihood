package share.livelihood.persongroup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.persongroup.api.service.IMenuService;
import share.livelihood.persongroup.domain.core.MenuInfoDomain;
import share.web.ApiController;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 21:26
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Menu")
@Api(value = "/livelihood/Menu", description = "菜单操作")
public class MenuController extends ApiController
{
    @Autowired
    private IMenuService iMenuService;

    @RequestMapping(value = "/add_menu", method = RequestMethod.POST)
    @ApiOperation(value = "添加菜单", notes = "添加菜单")
    @NoAuth
    public LiveResult<String> add_menu(@RequestBody MenuInfoDomain menuInfoDomain)
    {
        return ok(iMenuService.addMenuInfo(menuInfoDomain));
    }

    @RequestMapping(value = "/modify_menu", method = RequestMethod.POST)
    @ApiOperation(value = "修改菜单", notes = "修改菜单")
    @NoAuth
    public LiveResult<Boolean> modify_menu(@RequestBody MenuInfoDomain menuInfoDomain)
    {
        return ok(iMenuService.updateMenuInfo(menuInfoDomain));
    }

    @RequestMapping(value = "/del_menu", method = RequestMethod.GET)
    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @NoAuth
    public LiveResult<Boolean> del_menu(@RequestParam(name = "menuId") String menuId)
    {
        return ok(iMenuService.deleteMenuInfo(menuId));
    }

    @RequestMapping(value = "/query_menu", method = RequestMethod.GET)
    @ApiOperation(value = "获取菜单", notes = "获取菜单")
    @NoAuth
    public LiveResult<List<MenuInfoDomain>> query_menu()
    {
        return ok(iMenuService.getMenuList());
    }
}