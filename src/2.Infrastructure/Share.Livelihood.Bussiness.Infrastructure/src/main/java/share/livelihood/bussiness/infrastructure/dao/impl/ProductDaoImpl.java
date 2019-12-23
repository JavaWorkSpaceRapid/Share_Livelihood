package share.livelihood.bussiness.infrastructure.dao.impl;

import livelihood.bussiness.domain.core.*;
import livelihood.bussiness.domain.dao.IProductDao;
import livelihood.bussiness.infrastructure.mapper.IProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements IProductDao
{
    @Autowired
    private IProductMapper iProductMapper;

    @Override
    public String addProductInfo(ProductInfomationDomain productInfoDomain)
    {
        if(null != productInfoDomain)
        {
            iProductMapper.addProductInfo(productInfoDomain);
            updateProductRuleAttr(productInfoDomain);
            return productInfoDomain.getProduct_id();
        }
        return null;
    }

    @Override
    public String updateProductInfoByPid(ProductInfomationDomain productInfoDomain) {
        iProductMapper.updateProductInfoByPid(productInfoDomain);
        return productInfoDomain.getProduct_id();
    }

    @Override
    public List<ProductInfomationDomain> getListProductInfo() {
        return iProductMapper.getListProductInfo();
    }

    @Override
    public List<ProductInfomationDomain> getListProductInfoInPId(String productId)
    {
        return iProductMapper.getListProductInfoInPId(productId);
    }

    @Override
    public ProductInfomationDomain getProductInfoByProductId(String productId) {
        return iProductMapper.getProductInfoByProductId(productId);
    }

    @Override
    public List<ProductInfomationDomain> getListProductInfoByCId(String categoryId) {
        return iProductMapper.getListProductInfoByCId(categoryId);
    }

    @Override
    public void delProductInfoById(String productId) {
        iProductMapper.delProductInfoById(productId);
    }

    private void updateProductRuleAttr(ProductInfomationDomain productInfoDomain)
    {
        if(null !=productInfoDomain.getProductImgInfoDomainList()
                && productInfoDomain.getProductImgInfoDomainList().size() > 0)
        {
            iProductMapper.delProductImgByPid(productInfoDomain.getProduct_id());
            for(ProductImgInfoDomain productImgInfoDomain : productInfoDomain.getProductImgInfoDomainList())
            {
                productImgInfoDomain.setProduct_id(productInfoDomain.getProduct_id());
                iProductMapper.addProductImgInfo(productImgInfoDomain);
            }
        }
    }

    @Override
    public String updateProductAttr(ProductAttrInfoDomain productAttrInfoDomain) {
         iProductMapper.updateProductAttr(productAttrInfoDomain);
        return productAttrInfoDomain.getAttr_id();
    }

    @Override
    public String addProductRule(ProductRuleDomain productRuleDomain) {
         iProductMapper.addProductRule(productRuleDomain);
        return productRuleDomain.getProduct_rule_id();
    }

    @Override
    public String updateProductRule(ProductRuleDomain productRuleDomain) {
         iProductMapper.updateProductRule(productRuleDomain);
        return productRuleDomain.getProduct_rule_id();
    }

    @Override
    public ProductInfoDomain getProductInfoById(String productId)
    {
        return iProductMapper.getProductInfoById(productId);
    }

    @Override
    public List<ProductInfoDomain> getListProductInfoByCategoryId(String cateogryId)
    {
        return iProductMapper.getListProductInfoByCategoryId(cateogryId);
    }

    @Override
    public String addProductImgInfo(ProductImgInfoDomain productImgInfoDomain)
    {
        return String.valueOf(iProductMapper.addProductImgInfo(productImgInfoDomain));
    }

    @Override
    public List<ProductInfoDomain> getListProductInfoByBoilerId(String boilerId) {
        return iProductMapper.getListProductInfoByBoilerId(boilerId);
    }

    @Override
    public Boolean setProductStick(String product_id,String boilerId)
    {
        //将煮客Id下的其它菜品的置顶状态改为默认的
        iProductMapper.setProductStickByBid(boilerId);
        iProductMapper.setProductStick(product_id);
        return true;
    }

    @Override
    public String addProductAttr(ProductAttrInfoDomain productAttrInfoDomain)
    {
        return String.valueOf(iProductMapper.addProductAttr(productAttrInfoDomain));
    }

    @Override
    public List<ProductAttrInfoDomain> getProductAttrListByPId(String product_id)
    {
        return iProductMapper.getProductAttrListByPId(product_id);
    }

    @Override
    public Boolean delProductByPid(String productId)
    {
        iProductMapper.delProductByPid(productId);
        iProductMapper.delProductRuleByPid(productId);
        iProductMapper.delProductAttrByPid(productId);
        iProductMapper.delProductImgByPid(productId);
        iProductMapper.delProductMonthVolumeByPid(productId);
        return true;
    }
}
