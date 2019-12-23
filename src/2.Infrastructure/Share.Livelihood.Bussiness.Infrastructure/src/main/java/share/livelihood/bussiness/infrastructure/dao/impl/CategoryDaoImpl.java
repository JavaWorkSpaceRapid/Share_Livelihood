package share.livelihood.bussiness.infrastructure.dao.impl;

import livelihood.bussiness.domain.core.CategoryInfoDomain;
import livelihood.bussiness.domain.dao.ICategoryDao;
import livelihood.bussiness.infrastructure.mapper.ICategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by SunDawei on 2019/8/10.
 */
@Component
public class CategoryDaoImpl implements ICategoryDao
{
    @Autowired
    private ICategoryMapper iCategoryMapper;

    @Override
    public String addProductCategory(CategoryInfoDomain categoryInfoDomain)
    {
        return String.valueOf(iCategoryMapper.addProductCategory(categoryInfoDomain));
    }

    @Override
    public List<CategoryInfoDomain> getListProductCategoryByMerchantId(String merchantId)
    {
        return iCategoryMapper.getListProductCategoryByMerchantId(merchantId);
    }
}
