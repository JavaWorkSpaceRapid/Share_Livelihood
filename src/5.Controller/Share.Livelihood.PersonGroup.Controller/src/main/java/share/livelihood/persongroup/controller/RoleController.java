package share.livelihood.persongroup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.persongroup.api.service.IRoleService;
import org.springframework.web.bind.annotation.*;
import share.livelihood.persongroup.domain.core.RoleInfoDomain;
import share.livelihood.persongroup.domain.core.RoleMenuAuthDomain;
import share.web.ApiController;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/10/31 21:45
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Role")
@Api(value = "/livelihood/Role", description = "菜单操作")
public class RoleController extends ApiController
{
    private IRoleService iRoleService;

    @RequestMapping(value = "/addRoleInfo", method = RequestMethod.POST)
    @ApiOperation(value = "添加角色信息", notes = "添加角色信息")
    @NoAuth
    public LiveResult<String> addRoleInfo(@RequestBody RoleInfoDomain roleInfoDomain)
    {
        return ok(iRoleService.addRoleInfo(roleInfoDomain));
    }

    @RequestMapping(value = "/getListRoleInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取角色列表信息", notes = "获取角色列表信息")
    @NoAuth
    public LiveResult<List<RoleInfoDomain>> getListRoleInfo()
    {
        return ok(iRoleService.getListRoleInfo());
    }

    @RequestMapping(value = "/addRoleMenuInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取角色列表信息", notes = "获取角色列表信息")
    @NoAuth
    public LiveResult<String> addRoleMenuInfo(@RequestBody RoleMenuAuthDomain roleMenuAuthDomain)
    {
        return ok(iRoleService.addRoleMenuInfo(roleMenuAuthDomain));
    }

    @RequestMapping(value = "/getListRoleMenuInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取角色菜单列表信息", notes = "获取角色菜单列表信息")
    public LiveResult<List<RoleMenuAuthDomain>> getListRoleMenuInfo(@RequestParam(name = "roleId") String roleId)
    {
        return ok(iRoleService.getListRoleMenuInfo(roleId));
    }
}
