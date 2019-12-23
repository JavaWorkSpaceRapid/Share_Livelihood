package share.livelihood.bussiness.api.service;

import share.livelihood.bussiness.domain.core.order.OrderInfoDomain;

import java.util.List;

/**
 * Created by SunDawei on 2019/11/26.
 */
public interface IOrderService
{
    String addOrderInfo(OrderInfoDomain orderInfoDomain);

    Boolean editOrderInfo(OrderInfoDomain orderInfoDomain);

    Boolean editOrderStatus(String orderId, String status);

    List<OrderInfoDomain> getListOrderInfoByUserId(String userId);

    OrderInfoDomain getOrderInfoByOrderId(String orderId);

    List<OrderInfoDomain> getListOrderInfoByOrderIdStatus(String orderId, String status);

    List<OrderInfoDomain> getListOrderInfoByUserIdStatus(String userId, String status);
}
