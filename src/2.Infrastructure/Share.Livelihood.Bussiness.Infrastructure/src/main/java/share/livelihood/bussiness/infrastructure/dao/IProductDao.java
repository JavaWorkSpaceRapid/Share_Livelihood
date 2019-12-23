package share.livelihood.bussiness.infrastructure.dao;


import share.livelihood.bussiness.domain.core.ProductImgInfoDomain;
import share.livelihood.bussiness.domain.core.ProductInfomationDomain;

import java.util.List;

public interface IProductDao
{
    /**
     * 新增菜品信息
     * @param productInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-10
     */
    String addProductInfo(ProductInfomationDomain productInfoDomain);

    /**
     * 修改菜品信息
     * @param productInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-18
     */
    String updateProductInfoByPid(ProductInfomationDomain productInfoDomain);

    List<ProductInfomationDomain> getListProductInfo();

    List<ProductInfomationDomain> getListProductInfoInPId(String productId);

    ProductInfomationDomain getProductInfoByProductId(String productId);

    List<ProductInfomationDomain> getListProductInfoByCId(String categoryId);

    void delProductInfoById(String productId);

    /**
     * 新增产品图片信息
     * @param productImgInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-10
     */
    String addProductImgInfo(ProductImgInfoDomain productImgInfoDomain);
}
