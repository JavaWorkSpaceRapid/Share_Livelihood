package share.livelihood.bussiness.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.base.exceptions.BaseException;
import share.base.extensions.ObjectExtension;
import share.base.extensions.StringExtension;
import share.livelihood.bussiness.api.service.IOrderService;
import share.livelihood.bussiness.api.service.certificate.ICertificateService;
import share.livelihood.bussiness.domain.core.ProductInfomationDomain;
import share.livelihood.bussiness.domain.core.order.OrderCertificateDomain;
import share.livelihood.bussiness.domain.core.order.OrderInfoDomain;
import share.livelihood.bussiness.domain.core.order.OrderProductDomain;
import share.livelihood.bussiness.dto.certificate.CertificateOrderInfoRequest;
import share.livelihood.bussiness.dto.certificate.CertificateOrderInfoResponse;
import share.livelihood.bussiness.dto.certificate.info.SubCertificateOrderInfo;
import share.livelihood.bussiness.infrastructure.dao.IOrderDao;
import share.livelihood.bussiness.infrastructure.dao.IProductDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/26 20:37
 */
@Service
public class OrderServiceImpl implements IOrderService
{
    @Autowired
    private IOrderDao iOrderDao;

    @Autowired
    private IProductDao iProductDao;

    @Autowired
    private ICertificateService iCertificateService;

    @Override
    public String addOrderInfo(OrderInfoDomain orderInfoDomain)
    {
        try
        {
            if (null != orderInfoDomain)
            {
                String orderId = iOrderDao.addOrderInfo(orderInfoDomain);
                if (null != orderInfoDomain.getOrderProductDomainList() && orderInfoDomain.getOrderProductDomainList().size() > 0)
                {
                    List<OrderProductDomain> orderProductDomainList = new ArrayList<>();
                    List<OrderProductDomain> orderProductDomains = orderInfoDomain.getOrderProductDomainList();
                    String productIdStr = StringExtension.Empty;
                    Map<String, Object> productMap = new HashMap<>();
                    for (OrderProductDomain orderProductDomain : orderProductDomains)
                    {
                        orderProductDomain.setOrder_id(orderId);
                        orderProductDomainList.add(orderProductDomain);
                        productIdStr += orderProductDomain.getProduct_id() + ",";
                        productMap.put(orderProductDomain.getProduct_id(), orderProductDomain.getProduct_count());
                    }
                    productIdStr = productIdStr.substring(0, productIdStr.length() - 1);
                    double orderPrice = verifyOrderPrice(productIdStr, productMap, orderInfoDomain.getOrder_category_id(), orderId, orderInfoDomain.getSeller_id());
                    if (orderInfoDomain.getOrder_price() != orderPrice)
                    {
                        iOrderDao.editOrderPrice(orderPrice, orderId);
                    }
                    iOrderDao.addOrderProduct(orderProductDomainList);
                }
                return orderId;
            }
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BaseException(e.getMessage());
        }
    }

    /**
     * 获取订单价格
     * @param productIdStr
     * @param productMap
     * @param orderCategoryId
     * @param orderId
     * @param sellerId
     * @return double
     * @author SunDawei
     * @date 2019-12-05
     */
    private double verifyOrderPrice(String productIdStr, Map<String, Object> productMap, String orderCategoryId, String orderId, String sellerId)
    {
        double orderPrice = 0;
        if (ObjectExtension.isNotNull(productMap))
        {
            if (orderCategoryId.equals("0"))
            {
                List<ProductInfomationDomain> productInfomationDomains = iProductDao.getListProductInfoInPId(productIdStr);
                for (ProductInfomationDomain productInfomationDomain : productInfomationDomains)
                {
                    orderPrice = orderPrice + productInfomationDomain.getOne_jin() * Integer.parseInt(productMap.get(productInfomationDomain.getProduct_id()).toString());
                }
            }
            else
            {
                List<CertificateOrderInfoRequest> certificateOrderInfoRequests = new ArrayList<>();
                String[] pIdArr = productIdStr.split(",");
                for (String pId : pIdArr)
                {
                    CertificateOrderInfoRequest certificateOrderInfoRequest = new CertificateOrderInfoRequest();
                    certificateOrderInfoRequest.setCertificateDicCode(pId);
                    certificateOrderInfoRequest.setCount(Integer.parseInt(productMap.get(pId).toString()));
                    certificateOrderInfoRequests.add(certificateOrderInfoRequest);
                }
                System.out.println("orderId:"+orderId + ",sellerId:"+sellerId + ",certificateOrderInfoRequests:"+certificateOrderInfoRequests.toString());
                CertificateOrderInfoResponse certificateOrderInfoResponse = iCertificateService.lock4Sail(orderId, sellerId, certificateOrderInfoRequests);
                if(null != certificateOrderInfoResponse)
                {
                    List<SubCertificateOrderInfo> subCertificateOrderInfos = certificateOrderInfoResponse.getSubCertificateOrderInfos();
                    for(SubCertificateOrderInfo subCertificateOrderInfo:subCertificateOrderInfos)
                    {
                        OrderCertificateDomain orderCertificateDomain = new OrderCertificateDomain();
                        orderCertificateDomain.setCertificateDicCode(subCertificateOrderInfo.getCertificateDicCode());
                        String idStr = StringExtension.Empty;
                        for(String id:subCertificateOrderInfo.getIds())
                        {
                            idStr += id + ",";
                        }
                        idStr = idStr.substring(0,idStr.length()-1);
                        orderCertificateDomain.setOrder_id(orderId);
                        orderCertificateDomain.setCertificateIds(idStr);
                        iOrderDao.addOrderCertificate(orderCertificateDomain);
                    }
                }
                orderPrice = Double.valueOf(String.valueOf(certificateOrderInfoResponse.getTotalPrice()));
            }
        }
        return orderPrice;
    }

    @Override
    public Boolean editOrderInfo(OrderInfoDomain orderInfoDomain)
    {
        return iOrderDao.editOrderInfo(orderInfoDomain);
    }

    @Override
    public Boolean editOrderStatus(String orderId, String status)
    {
        if(status.equals("-1"))
        {
            CertificateOrderInfoResponse certificateOrderInfoResponse = new CertificateOrderInfoResponse();
            List<SubCertificateOrderInfo> subCertificateOrderInfos = new ArrayList<>();
            List<OrderCertificateDomain> orderCertificateDomains = iOrderDao.getListOrderCertificateByOrderId(orderId);
            if(null != orderCertificateDomains && orderCertificateDomains.size() > 0)
            {
                for(OrderCertificateDomain orderCertificateDomain : orderCertificateDomains)
                {
                    String[] idArr = orderCertificateDomain.getCertificateIds().split(",");
                    List<String> idList = new ArrayList<>();
                    for (String idStr : idArr)
                    {
                        idList.add(idStr);
                    }
                    SubCertificateOrderInfo subCertificateOrderInfo = new SubCertificateOrderInfo();
                    subCertificateOrderInfo.setCertificateDicCode(orderCertificateDomain.getCertificateDicCode());
                    subCertificateOrderInfo.setIds(idList);
                    subCertificateOrderInfos.add(subCertificateOrderInfo);
                }
            }
            certificateOrderInfoResponse.setSubCertificateOrderInfos(subCertificateOrderInfos);
            iCertificateService.cancel4Exchange(orderId,certificateOrderInfoResponse);
        }
        return iOrderDao.editOrderStatus(orderId, status);
    }

    @Override
    public List<OrderInfoDomain> getListOrderInfoByUserId(String userId)
    {
        return iOrderDao.getListOrderInfoByUserId(userId);
    }

    @Override
    public OrderInfoDomain getOrderInfoByOrderId(String orderId)
    {
        return iOrderDao.getOrderInfoByOrderId(orderId);
    }

    @Override
    public List<OrderInfoDomain> getListOrderInfoByOrderIdStatus(String orderId, String status)
    {
        return iOrderDao.getListOrderInfoByOrderIdStatus(orderId,status);
    }

    @Override
    public List<OrderInfoDomain> getListOrderInfoByUserIdStatus(String userId, String status)
    {
        return iOrderDao.getListOrderInfoByUserIdStatus(userId,status);
    }
}
