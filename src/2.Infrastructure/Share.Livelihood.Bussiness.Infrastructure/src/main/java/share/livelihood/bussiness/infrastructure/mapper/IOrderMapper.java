package share.livelihood.bussiness.infrastructure.mapper;

import livelihood.bussiness.domain.core.order.OrderCertificateDomain;
import livelihood.bussiness.domain.core.order.OrderInfoDomain;
import livelihood.bussiness.domain.core.order.OrderProductDomain;

import java.util.List;
import java.util.Map;

/**
 * Created by SunDawei on 2019/11/26.
 */
public interface IOrderMapper
{
    Integer addOrderInfo(OrderInfoDomain orderInfoDomain);

    void editOrderPrice(Map<String, Object> map);

    void addOrderProduct(List<OrderProductDomain> list);

    void addOrderCertificate(OrderCertificateDomain orderCertificateDomain);

    List<OrderCertificateDomain> getListOrderCertificateByOrderId(String orderId);

    List<OrderProductDomain> getListOrderProductByOrderId(String order_id);

    void editOrderInfo(OrderInfoDomain orderInfoDomain);

    void editOrderStatus(Map<String, String> map);

    List<OrderInfoDomain> getListOrderInfoByUserId(String userId);

    OrderInfoDomain getOrderInfoByOrderId(String orderId);

    List<OrderInfoDomain> getListOrderInfoByOrderIdStatus(Map<String, String> map);

    List<OrderInfoDomain> getListOrderInfoByUserIdStatus(Map<String, String> map);
}
