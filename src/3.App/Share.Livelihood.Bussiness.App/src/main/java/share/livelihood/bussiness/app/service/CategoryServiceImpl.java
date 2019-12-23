package share.livelihood.bussiness.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.livelihood.bussiness.api.service.ICategoryService;
import share.livelihood.bussiness.domain.core.CategoryInfoDomain;
import share.livelihood.bussiness.infrastructure.dao.ICategoryDao;

import java.util.List;

/**
 * Created by SunDawei on 2019/8/10.
 */
@Service
public class CategoryServiceImpl implements ICategoryService
{
    @Autowired
    private ICategoryDao iCategoryDao;

    @Override
    public String addProductCategory(CategoryInfoDomain categoryInfoDomain)
    {
        return  iCategoryDao.addProductCategory(categoryInfoDomain);
    }

    @Override
    public List<CategoryInfoDomain> getListProductCategoryByMerchantId(String merchantId)
    {
        return iCategoryDao.getListProductCategoryByMerchantId(merchantId);
    }
}
