package share.livelihood.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.bussiness.api.service.IProductService;
import share.livelihood.bussiness.domain.core.ProductImgInfoDomain;
import share.livelihood.bussiness.domain.core.ProductInfomationDomain;
import share.web.ApiController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SunDawei on 2019/8/10.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Product")
@Api(value = "/livelihood/Product", description = "产品菜品")
public class ProductController extends ApiController
{
    @Autowired
    private IProductService iProductService;

    @RequestMapping(value = "/addProductInfo", method = RequestMethod.POST)
    @ApiOperation(value = "新增产品", notes = "新增产品")
    @NoAuth
    public LiveResult<String> addProductInfo(@RequestBody ProductInfomationDomain productInfoDomain)
    {
        return ok(iProductService.addProductInfo(productInfoDomain));
    }

    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    @ApiOperation(value = "新增菜品图片", notes = "新增菜品")
    @NoAuth
    public LiveResult<String> uploadImg(HttpServletRequest req, @RequestParam("file") MultipartFile file)
    {
        return ok(iProductService.uploadImg(file));
    }

    @RequestMapping(value = "/updateProductInfoByPid", method = RequestMethod.POST)
    @ApiOperation(value = "修改菜品信息", notes = "修改菜品信息")
    @NoAuth
    public LiveResult<String> updateProductInfoByPid(@RequestBody ProductInfomationDomain productInfoDomain)
    {
        return ok(iProductService.updateProductInfoByPid(productInfoDomain));
    }

    @RequestMapping(value = "/delProductInfoById", method = RequestMethod.GET)
    @ApiOperation(value = "删除菜品信息", notes = "删除菜品信息")
    @NoAuth
    public LiveResult<Boolean> delProductInfoById(@RequestParam(name = "productId") String productId)
    {
        iProductService.delProductInfoById(productId);
        return ok(true);
    }

    @RequestMapping(value = "/getListProductInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取商品列表", notes = "获取商品列表")
    @NoAuth
    public LiveResult<List<ProductInfomationDomain>> getListProductInfo()
    {
        return ok(iProductService.getListProductInfo());
    }

    @RequestMapping(value = "/getProductInfoByProductId", method = RequestMethod.GET)
    @ApiOperation(value = "根据商品Id，获取商品详细信息", notes = "根据商品Id，获取商品详细信息")
    @NoAuth
    public LiveResult<ProductInfomationDomain> getProductInfoByProductId(@RequestParam(name = "productId") String productId)
    {
        return ok(iProductService.getProductInfoByProductId(productId));
    }

    @RequestMapping(value = "/getListProductInfoByCId", method = RequestMethod.GET)
    @ApiOperation(value = "根据商品Id，获取商品详细信息", notes = "根据商品Id，获取商品详细信息")
    @NoAuth
    public LiveResult<List<ProductInfomationDomain>> getListProductInfoByCId(@RequestParam(name = "categoryId") String categoryId)
    {
        return ok(iProductService.getListProductInfoByCId(categoryId));
    }

    @RequestMapping(value = "/addProductImgInfo", method = RequestMethod.GET)
    @ApiOperation(value = "新增菜品", notes = "新增菜品")
    @NoAuth
    public LiveResult<String> addProductImgInfo(@RequestBody ProductImgInfoDomain productImgInfoDomain)
    {
        return ok(iProductService.addProductImgInfo(productImgInfoDomain));
    }

}
