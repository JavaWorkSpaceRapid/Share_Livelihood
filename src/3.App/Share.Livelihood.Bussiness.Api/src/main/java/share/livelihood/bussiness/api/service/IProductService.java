package share.livelihood.bussiness.api.service;

import org.springframework.web.multipart.MultipartFile;
import share.livelihood.bussiness.domain.core.ProductImgInfoDomain;
import share.livelihood.bussiness.domain.core.ProductInfomationDomain;

import java.util.List;

/**
 * Created by SunDawei on 2019/8/10.
 */
public interface IProductService
{
    /**
     * 新增菜品信息
     *
     * @param productInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-18
     */
    String addProductInfo(ProductInfomationDomain productInfoDomain);

    /**
     * 修改菜品信息
     *
     * @param productInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-18
     */
    String updateProductInfoByPid(ProductInfomationDomain productInfoDomain);

    List<ProductInfomationDomain> getListProductInfo();

    ProductInfomationDomain getProductInfoByProductId(String productId);

    List<ProductInfomationDomain> getListProductInfoByCId(String categoryId);

    void delProductInfoById(String productId);

    /**
     * 新增产品图片信息
     *
     * @param productImgInfoDomain
     * @return String
     * @author SunDawei
     * @date 2019-08-10
     */
    String addProductImgInfo(ProductImgInfoDomain productImgInfoDomain);

    String uploadImg(MultipartFile file);
}
