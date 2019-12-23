package share.livelihood.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.bussiness.api.service.ICategoryService;
import share.livelihood.bussiness.domain.core.CategoryInfoDomain;
import share.web.ApiController;

import java.util.List;

/**
 * Created by SunDawei on 2019/8/10.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Category")
@Api(value = "/livelihood/Category", description = "菜品分类")
public class CategoryController extends ApiController
{
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(value = "/addProductCategory", method = RequestMethod.POST)
    @ApiOperation(value = "新增菜品分类", notes = "新增菜品分类")
    @NoAuth
    public LiveResult<String> addProductCategory(@RequestBody CategoryInfoDomain categoryInfoDomain)
    {
        return ok(iCategoryService.addProductCategory(categoryInfoDomain));
    }

    @RequestMapping(value = "/getListProductCategoryByBoilerId", method = RequestMethod.GET)
    @ApiOperation(value = "获取菜品分类列表", notes = "获取菜品分类列表")
    @NoAuth
    public LiveResult<List<CategoryInfoDomain>> getListProductCategoryByMerchantId(@RequestParam(name = "boilerId") String boilerId)
    {
        return ok(iCategoryService.getListProductCategoryByMerchantId(boilerId));
    }
}
