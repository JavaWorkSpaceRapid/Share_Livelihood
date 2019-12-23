package share.livelihood.bussiness.app.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.base.exceptions.BaseException;
import share.livelihood.bussiness.api.service.IOrderService;
import share.livelihood.bussiness.api.service.IWxPayService;
import share.livelihood.bussiness.app.utils.Constant;
import share.livelihood.bussiness.app.utils.WXConfigUtil;
import share.livelihood.bussiness.domain.core.order.OrderInfoDomain;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/12/20 18:08
 */
@Service
public class WxPayServiceImpl implements IWxPayService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    private WXConfigUtil wxConfigUtil;

    @Autowired
    private IOrderService iOrderService;

    @Override
    public Map<String, Object> unifiedOrder(String orderNo, String openId, String body)
    {
        Map<String, Object> resultRtnMap = new HashMap<>();
        try
        {
            OrderInfoDomain orderInfoDomain = iOrderService.getOrderInfoByOrderId(orderNo);
            if(null != orderInfoDomain && orderInfoDomain.getOrder_price() > 0)
            {
                Map<String, String> resultMap = wxConfigUtil.getResultMap(body, orderNo, orderInfoDomain.getOrder_price());
                Map<String, String> responseMap = wxConfigUtil.getResponseMap(resultMap);
                Map<String, String> returnMap = wxConfigUtil.getReturnMap(responseMap);
                resultRtnMap.put(Constant.DATA, returnMap);
            }
            else
            {
                throw new BaseException(Constant.ORDER_UNUSUAL_MSG);
            }
            return resultRtnMap;
        }
        catch (Exception e)
        {
            LOGGER.error("订单号：{}，错误信息：{}", orderNo, e.getMessage());
            throw new BaseException(Constant.WECHAT_PAY_ORDER_FAIL + ":" + e.getMessage());
        }
    }

    /**
     * 回调
     *
     * @param request
     * @return String
     * @throws Exception
     * @author SunDawei
     * @date 2019-12-20
     */
    @Override
    public String notify(HttpServletRequest request)
    {
        InputStream is = null;
        String xmlBack = Constant.XML_BACK_FAIL;
        try
        {
            is = request.getInputStream();
            // 将InputStream转换成String
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            String out_trade_no = wxConfigUtil.notifyUtil(sb.toString());
            if (null != out_trade_no)
            {
                iOrderService.editOrderStatus(out_trade_no, "1");
                xmlBack = Constant.XML_BACK_SUCCESS;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new BaseException(e.getMessage());
        }
        finally
        {
            if (is != null)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return xmlBack;
    }

    @Override
    public Map<String, Object> refund(String orderNo, double amount, String refundReason)
    {
        if (StringUtils.isBlank(orderNo))
        {
            throw new BaseException(Constant.ORDER_NO_NOT_NULL);
        }
        if (amount <= 0)
        {
            throw new BaseException(Constant.BACK_MONEY_MUST_GIT_ZEOR);
        }
        Map<String, String> responseMap = wxConfigUtil.getRefundResponseMapMap(orderNo, refundReason, amount);
        String return_code = responseMap.get(Constant.RETURN_CODE);   //返回状态码
        String return_msg = responseMap.get(Constant.RETURN_MSG);     //返回信息
        if (Constant.SUCCESS.equals(return_code))
        {
            String result_code = responseMap.get(Constant.RESULT_CODE);       //业务结果
            String err_code_des = responseMap.get(Constant.ERR_CODE_DES);     //错误代码描述
            if (Constant.SUCCESS.equals(result_code))
            {
                //表示退款申请接受成功，结果通过退款查询接口查询
                //修改用户订单状态为退款申请中或已退款。退款异步通知根据需求，可选
                //
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put(Constant.DATA, Constant.REFUND_ALIPAY_SUCCESS);
                return resultMap;
            }
            else
            {
                LOGGER.info("订单号:{}错误信息:{}", orderNo, err_code_des);
                throw new BaseException(err_code_des);
            }
        }
        else
        {
            LOGGER.info("订单号:{}错误信息:{}", orderNo, return_msg);
            throw new BaseException(return_msg);
        }
    }
}
