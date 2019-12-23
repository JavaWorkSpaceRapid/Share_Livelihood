package share.livelihood.bussiness.infrastructure.mapper;

import livelihood.bussiness.domain.core.*;

import java.util.List;

public interface IProductMapper
{
    Integer addProductInfo(ProductInfomationDomain productInfoDomain);

    /**
     * 修改菜品信息
     * @param productInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-18
     */
    void updateProductInfoByPid(ProductInfomationDomain productInfoDomain);

    List<ProductInfomationDomain> getListProductInfo();

    List<ProductInfomationDomain> getListProductInfoInPId(String productId);

    ProductInfomationDomain getProductInfoByProductId(String productId);

    List<ProductInfomationDomain> getListProductInfoByCId(String categoryId);

    void delProductInfoById(String productId);

    void delProductImgByPid(String productId);

    /**
     * 修改菜品属性
     * @param productAttrInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-18
     */
    void updateProductAttr(ProductAttrInfoDomain productAttrInfoDomain);

    Integer addProductAttr(ProductAttrInfoDomain productAttrInfoDomain);

    void delProductAttrByPid(String productId);

    /**
     * 修改菜品属性
     * @param productRuleDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-18
     */
    Integer addProductRule(ProductRuleDomain productRuleDomain);

    void delProductRuleByPid(String productId);

    /**
     * 修改菜品属性
     * @param productRuleDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-18
     */
    void updateProductRule(ProductRuleDomain productRuleDomain);

    ProductInfoDomain getProductInfoById(String productId);

    List<ProductInfoDomain> getListProductInfoByCategoryId(String cateogryId);

    Integer addProductImgInfo(ProductImgInfoDomain productImgInfoDomain);

    /**
     * 根据产品的分类Id，获取产品列表信息
     * @param boilerId
     * @return List<ProductInfoDomain>
     * @author SunDawei
     * @date 2019-08-10
     */
    List<ProductInfoDomain> getListProductInfoByBoilerId(String boilerId);

    List<BoilerCategoryProductDomain> getBoilerProductList(String boiler_id);


    void setProductStick(String product_id);

    void setProductStickByBid(String boilerId);

    List<ProductAttrInfoDomain> getProductAttrListByPId(String product_id);

    void delProductByPid(String productId);

    void delProductMonthVolumeByPid(String productId);
}
