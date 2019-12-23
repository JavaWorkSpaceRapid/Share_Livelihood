package share.livelihood.bussiness.infrastructure.mapper;

import share.livelihood.bussiness.domain.core.CategoryInfoDomain;

import java.util.List;

/**
 * Created by SunDawei on 2019/8/10.
 */
public interface ICategoryMapper
{
    Integer addProductCategory(CategoryInfoDomain categoryInfoDomain);

    List<CategoryInfoDomain> getListProductCategoryByMerchantId(String merchantId);
}
