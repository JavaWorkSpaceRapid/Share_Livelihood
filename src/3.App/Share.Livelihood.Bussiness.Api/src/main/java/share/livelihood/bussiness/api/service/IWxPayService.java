package share.livelihood.bussiness.api.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/12/20 18:07
 */
public interface IWxPayService
{
    /**
     * @Description: 微信支付统一下单
     * @param orderNo: 订单编号
     * @param openId: 实际支付金额
     * @param body: 订单描述
     * @Author: SunDawei
     * @Date: 2019-12-20
     * @return
     */
    Map<String,Object> unifiedOrder(String orderNo, String openId, String body) ;

    /**
     * @Description: 订单支付异步通知
     * @param request: 微信异步通知消息字符串
     * @Author: SunDawei
     * @Date: 2019-12-20
     */
    String notify(HttpServletRequest request);

    /**
     * @Description: 退款
     * @param orderNo: 订单编号
     * @param amount: 实际支付金额
     * @param refundReason: 退款原因
     * @Author: SunDawei
     * @Date: 2019-12-20
     * @return
     */
    Map<String,Object> refund(String orderNo, double amount, String refundReason);
}
