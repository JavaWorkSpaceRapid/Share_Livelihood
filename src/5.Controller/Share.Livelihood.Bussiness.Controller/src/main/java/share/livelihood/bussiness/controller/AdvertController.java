package share.livelihood.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.bussiness.api.service.IAdvertisingService;
import share.livelihood.bussiness.domain.core.AdvertisingDomain;
import share.web.ApiController;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/14 19:49
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Advert")
@Api(value = "/livelihood/Advert", description = "首页广告")
public class AdvertController extends ApiController
{
    @Autowired
    private IAdvertisingService iAdvertisingService;

    @RequestMapping(value = "/add_advertising", method = RequestMethod.POST)
    @ApiOperation(value = "添加广告", notes = "添加广告")
    @NoAuth
    public LiveResult<String> add_advertising(@RequestBody AdvertisingDomain advertisingDomain)
    {
        return ok(iAdvertisingService.addAdvertising(advertisingDomain));
    }

    @RequestMapping(value = "/modify_advertising", method = RequestMethod.GET)
    @ApiOperation(value = "修改广告", notes = "修改广告")
    @NoAuth
    public LiveResult<Boolean> modify_advertising(@RequestBody AdvertisingDomain advertisingDomain)
    {
        return ok(iAdvertisingService.updateAdvertising(advertisingDomain));
    }

    @RequestMapping(value = "/del_advertising", method = RequestMethod.GET)
    @ApiOperation(value = "删除广告", notes = "删除广告")
    @NoAuth
    public LiveResult<Boolean> del_advertising(@RequestParam(name = "advertId") String advertId)
    {
        return ok(iAdvertisingService.deleteAdvertising(advertId));
    }

    @RequestMapping(value = "/query_advertising", method = RequestMethod.GET)
    @ApiOperation(value = "获取广告", notes = "获取广告")
    @NoAuth
    public LiveResult<List<AdvertisingDomain>> query_advertising()
    {
        return ok(iAdvertisingService.getAdvertising());
    }
}
