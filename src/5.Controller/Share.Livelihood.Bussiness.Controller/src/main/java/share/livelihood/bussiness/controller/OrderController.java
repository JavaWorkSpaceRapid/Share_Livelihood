package share.livelihood.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.bussiness.api.service.IOrderService;
import share.livelihood.bussiness.api.service.IWxPayService;
import share.livelihood.bussiness.domain.core.order.OrderInfoDomain;
import share.web.ApiController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 购物车，订单处理控制器
 *
 * @author SunDawei
 * @date 2019/11/2 23:08
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Order")
@Api(value = "/livelihood/Order", description = "订单信息")
public class OrderController extends ApiController
{

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IWxPayService iWxPayService;

    @RequestMapping(value = "/addOrderInfo", method = RequestMethod.POST)
    @ApiOperation(value = "添加订单信息", notes = "添加订单信息")
    @NoAuth
    public LiveResult<String> addOrderInfo(@RequestBody OrderInfoDomain orderInfoDomain)
    {
        return ok(iOrderService.addOrderInfo(orderInfoDomain));
    }

    @RequestMapping(value = "/editOrderInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改订单信息", notes = "修改订单信息")
    @NoAuth
    public LiveResult<Boolean> editOrderInfo(@RequestBody OrderInfoDomain orderInfoDomain)
    {
        return ok(iOrderService.editOrderInfo(orderInfoDomain));
    }

    @RequestMapping(value = "/editOrderStatus", method = RequestMethod.GET)
    @ApiOperation(value = "修改订单状态", notes = "修改订单状态")
    @NoAuth
    public LiveResult<Boolean> editOrderStatus(@RequestParam(name = "orderId") String orderId,
                                               @RequestParam(name = "status") String status)
    {
        return ok(iOrderService.editOrderStatus(orderId, status));
    }

    @RequestMapping(value = "/getListOrderInfoByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "根据UserId,获取订单列表信息", notes = "根据UserId,获取订单列表信息")
    @NoAuth
    public LiveResult<List<OrderInfoDomain>> getListOrderInfoByUserId(@RequestParam(name = "userId") String userId)
    {
        return ok(iOrderService.getListOrderInfoByUserId(userId));
    }

    @RequestMapping(value = "/getOrderInfoByOrderId", method = RequestMethod.GET)
    @ApiOperation(value = "根据OrderId,获取订单信息", notes = "根据OrderId,获取订单信息")
    @NoAuth
    public LiveResult<OrderInfoDomain> getOrderInfoByOrderId(@RequestParam(name = "orderId") String orderId)
    {
        return ok(iOrderService.getOrderInfoByOrderId(orderId));
    }

    @RequestMapping(value = "/getListOrderInfoByOrderIdStatus", method = RequestMethod.GET)
    @ApiOperation(value = "根据OrderId及状态,获取订单信息", notes = "根据OrderId及状态,获取订单信息")
    @NoAuth
    public LiveResult<List<OrderInfoDomain>> getListOrderInfoByOrderIdStatus(@RequestParam(name = "orderId") String orderId,
                                                                             @RequestParam(name = "status") String status)
    {
        return ok(iOrderService.getListOrderInfoByOrderIdStatus(orderId, status));
    }

    @RequestMapping(value = "/getListOrderInfoByUserIdStatus", method = RequestMethod.GET)
    @ApiOperation(value = "根据OrderId及状态,获取订单信息", notes = "根据OrderId及状态,获取订单信息")
    @NoAuth
    public LiveResult<List<OrderInfoDomain>> getListOrderInfoByUserIdStatus(@RequestParam(name = "userId") String userId,
                                                                            @RequestParam(name = "status") String status)
    {
        return ok(iOrderService.getListOrderInfoByUserIdStatus(userId, status));
    }

    @RequestMapping(value = "/wxPay", method = RequestMethod.POST)
    @ApiOperation(notes = "调起微信支付", value = "调起微信支付", produces = "application/json")
    @NoAuth
    public LiveResult<Map<String,Object>> unifiedOrder(
            @ApiParam(value = "订单号") @RequestParam(name = "orderNo") String orderNo,
            @ApiParam(value = "订单号") @RequestParam(name = "openId") String openId,
            @ApiParam(value = "商品名称") @RequestParam(name = "body") String body)
    {
            // 1、验证订单是否存在
            // 2、开始微信支付统一下单
            return ok(iWxPayService.unifiedOrder(orderNo,openId,body));//系统通用的返回结果集，见文章末尾
    }

    /**
     * 微信支付异步通知
     */
    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    @ApiOperation(notes = "微信支付异步通知", value = "微信支付异步通知", produces = "application/json")
    public LiveResult<String> notify(HttpServletRequest request)
    {
        return ok(iWxPayService.notify(request));
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ApiOperation(notes = "退款", value = "退款", produces = "application/json")
    public LiveResult<Map<String, Object>> refund(@ApiParam(value = "订单号") @RequestParam String orderNo,
                                                  @ApiParam(value = "退款金额") @RequestParam double amount,
                                                  @ApiParam(value = "退款原因") @RequestParam(required = false) String refundReason)
    {

        return ok(iWxPayService.refund(orderNo, amount, refundReason));
    }


}
