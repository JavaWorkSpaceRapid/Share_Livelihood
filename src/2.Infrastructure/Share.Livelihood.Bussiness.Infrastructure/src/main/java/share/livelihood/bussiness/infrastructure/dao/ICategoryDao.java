package share.livelihood.bussiness.infrastructure.dao;


import share.livelihood.bussiness.domain.core.CategoryInfoDomain;

import java.util.List;

/**
 * Created by SunDawei on 2019/8/10.
 */
public interface ICategoryDao
{
    String addProductCategory(CategoryInfoDomain categoryInfoDomain);

    List<CategoryInfoDomain> getListProductCategoryByMerchantId(String merchantId);
}
