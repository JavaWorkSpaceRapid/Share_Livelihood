package share.livelihood.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.bussiness.api.service.IShoppingCarService;
import share.livelihood.bussiness.domain.core.ShoppingCarDomain;
import share.web.ApiController;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/21 21:12
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/ShoppingCar")
@Api(value = "/livelihood/ShoppingCar", description = "购物车")
public class ShoppingCarController extends ApiController
{
    @Autowired
    private IShoppingCarService iShoppingCarService;

    @RequestMapping(value = "/addShoppingCar", method = RequestMethod.POST)
    @ApiOperation(value = "新增收穫地址", notes = "新增收穫地址")
    @NoAuth
    public LiveResult<String> addShoppingCar(@RequestBody ShoppingCarDomain shoppingCarDomain)
    {
        return ok(iShoppingCarService.addShoppingCar(shoppingCarDomain));
    }

    @RequestMapping(value = "/editShoppingCarNum", method = RequestMethod.GET)
    @ApiOperation(value = "編輯收穫地址", notes = "編輯收穫地址")
    @NoAuth
    public LiveResult<Boolean> editShoppingCarNum(@RequestParam(name = "productCount") int productCount,
                                                  @RequestParam(name = "carId") String carId)
    {
        return ok(iShoppingCarService.editShoppingCarNum(productCount,carId));
    }

    @RequestMapping(value = "/deleteShoppingCarByID", method = RequestMethod.GET)
    @ApiOperation(value = "刪除收穫地址", notes = "刪除收穫地址")
    @NoAuth
    public LiveResult<Boolean> deleteShoppingCarByID(@RequestParam(name = "carId") String carId)
    {
        return ok(iShoppingCarService.deleteShoppingCarByID(carId));

    }

    @RequestMapping(value = "/getListShoppingCar", method = RequestMethod.GET)
    @ApiOperation(value = "刪除收穫地址", notes = "刪除收穫地址")
    @NoAuth
    public LiveResult<List<ShoppingCarDomain>> getListShoppingCar(@RequestParam(name = "userId") String userId)
    {
        return ok(iShoppingCarService.getListShoppingCar(userId));

    }
}
