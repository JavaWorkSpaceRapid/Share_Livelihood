package share.livelihood.bussiness.infrastructure.dao;


import share.livelihood.bussiness.domain.core.order.OrderCertificateDomain;
import share.livelihood.bussiness.domain.core.order.OrderInfoDomain;
import share.livelihood.bussiness.domain.core.order.OrderProductDomain;

import java.util.List;

/**
 * Created by SunDawei on 2019/11/26.
 */
public interface IOrderDao
{
    String addOrderInfo(OrderInfoDomain orderInfoDomain);

    void editOrderPrice(double orderPrice, String orderId);

    void addOrderProduct(List<OrderProductDomain> orderProductDomains);

    void addOrderCertificate(OrderCertificateDomain orderCertificateDomain);

    List<OrderCertificateDomain> getListOrderCertificateByOrderId(String orderId);

    List<OrderProductDomain> getListOrderProductByOrderId(String orderId);

    Boolean editOrderInfo(OrderInfoDomain orderInfoDomain);

    Boolean editOrderStatus(String orderId, String status);

    List<OrderInfoDomain> getListOrderInfoByUserId(String userId);

    OrderInfoDomain getOrderInfoByOrderId(String orderId);

    List<OrderInfoDomain> getListOrderInfoByOrderIdStatus(String orderId, String status);

    List<OrderInfoDomain> getListOrderInfoByUserIdStatus(String userId, String status);
}
