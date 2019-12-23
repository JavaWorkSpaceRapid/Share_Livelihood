package share.livelihood.bussiness.infrastructure.dao.impl;/**
 * Created by Administrator on 2019/11/26.
 */

import livelihood.bussiness.domain.core.order.OrderCertificateDomain;
import livelihood.bussiness.domain.core.order.OrderInfoDomain;
import livelihood.bussiness.domain.core.order.OrderProductDomain;
import livelihood.bussiness.domain.dao.IOrderDao;
import livelihood.bussiness.infrastructure.mapper.IOrderMapper;
import livelihood.bussiness.infrastructure.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/26 20:39
 */
@Service
public class OrderDaoImpl implements IOrderDao
{
    @Autowired
    private IOrderMapper iOrderMapper;

    @Autowired
    private ToolUtil toolUtil;

    @Override
    public String addOrderInfo(OrderInfoDomain orderInfoDomain)
    {
        if(null != orderInfoDomain)
        {
            orderInfoDomain.setOrder_id(toolUtil.getOrderIdByTime());
            iOrderMapper.addOrderInfo(orderInfoDomain);
            return orderInfoDomain.getOrder_id();
        }
        return null;
    }

    @Override
    public void editOrderPrice(double orderPrice, String orderId)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        map.put("orderPrice",orderPrice);
        iOrderMapper.editOrderPrice(map);
    }

    @Override
    public void addOrderProduct(List<OrderProductDomain> orderProductDomains)
    {
        iOrderMapper.addOrderProduct(orderProductDomains);
    }

    @Override
    public void addOrderCertificate(OrderCertificateDomain orderCertificateDomain)
    {
        iOrderMapper.addOrderCertificate(orderCertificateDomain);
    }

    @Override
    public List<OrderCertificateDomain> getListOrderCertificateByOrderId(String orderId)
    {
        return iOrderMapper.getListOrderCertificateByOrderId(orderId);
    }

    @Override
    public List<OrderProductDomain> getListOrderProductByOrderId(String orderId)
    {
        return iOrderMapper.getListOrderProductByOrderId(orderId);
    }

    @Override
    public Boolean editOrderInfo(OrderInfoDomain orderInfoDomain)
    {
        iOrderMapper.editOrderInfo(orderInfoDomain);
        return true;
    }

    @Override
    public Boolean editOrderStatus(String orderId, String status)
    {
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderId);
        map.put("status",status);
        iOrderMapper.editOrderStatus(map);
        return true;
    }

    @Override
    public List<OrderInfoDomain> getListOrderInfoByUserId(String userId)
    {
        return iOrderMapper.getListOrderInfoByUserId(userId);
    }

    @Override
    public OrderInfoDomain getOrderInfoByOrderId(String orderId)
    {
        return iOrderMapper.getOrderInfoByOrderId(orderId);
    }

    @Override
    public List<OrderInfoDomain> getListOrderInfoByOrderIdStatus(String orderId, String status)
    {
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderId);
        map.put("status",status);
        return iOrderMapper.getListOrderInfoByOrderIdStatus(map);
    }

    @Override
    public List<OrderInfoDomain> getListOrderInfoByUserIdStatus(String userId, String status)
    {
        Map<String,String> paramsMap = new HashMap<>();
        paramsMap.put("userId",userId);
        paramsMap.put("status",status);
        return iOrderMapper.getListOrderInfoByUserIdStatus(paramsMap);
    }
}
