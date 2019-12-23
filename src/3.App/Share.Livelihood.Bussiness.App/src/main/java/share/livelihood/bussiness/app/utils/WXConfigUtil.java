package share.livelihood.bussiness.app.utils;

import lycan.base.exceptions.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author SunDawei
 * @date 2019/12/20
 */
@Component
public class WXConfigUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(WXConfigUtil.class);

    @Autowired
    private WxPayAppConfig wxPayAppConfig;

    public Map<String,String> getResultMap(String body,String orderNo,double amount)
    {
        try
        {
            WXPay wxPay = new WXPay(wxPayAppConfig);
            Map<String, String> requestMap = new HashMap<>();
            requestMap.put(Constant.BODY, body);                                     // 商品描述
            requestMap.put(Constant.OUT_TRADE_NO, orderNo);                          // 商户订单号
            requestMap.put(Constant.TOTAL_FEE, String.valueOf((int) (amount * 100)));   // 总金额
            requestMap.put(Constant.SPBILL_CREATE_IP, InetAddress.getLocalHost().getHostAddress().toString()); // 终端IP
            requestMap.put(Constant.TRADE_TYPE, Constant.TRADE_TYPE_JSAPI);                              // App支付类型
            requestMap.put(Constant.NOTIFY_URL, wxPayAppConfig.getPayNotifyUrl());   // 接收微信支付异步通知回调地址
            Map<String,String> resultMap = wxPay.unifiedOrder(requestMap);
            return resultMap;
        }
        catch (Exception e)
        {
            LOGGER.error("ResultMap：" + e.getMessage());
            throw new BaseException(e.getMessage());
        }
    }

    public Map<String,String> getResponseMap(Map<String,String> resultMap)
    {
        Map<String, String> responseMap = new HashMap<>();
        //获取返回码
        String returnCode = resultMap.get(Constant.RETURN_CODE);
        String returnMsg = resultMap.get(Constant.RETURN_MSG);
        //若返回码为SUCCESS，则会返回一个result_code,再对该result_code进行判断
        if (Constant.SUCCESS.equals(returnCode))
        {
            String resultCode = resultMap.get(Constant.RESULT_CODE);
            String errCodeDes = resultMap.get(Constant.ERR_CODE_DES);
            if (Constant.SUCCESS.equals(resultCode))
            {
                responseMap = resultMap;
            }
        }
        if (responseMap == null || responseMap.isEmpty())
        {
            throw new BaseException(Constant.GET_PAY_FAIL);
        }
        return responseMap;
    }

    public Map<String,String> getReturnMap(Map<String,String> responseMap)
    {
        try
        {
            Map<String, String> returnMap = new HashMap<>();
            // 3、签名生成算法
            Long time = System.currentTimeMillis() / 1000;
            String timestamp = time.toString();
            returnMap.put(Constant.APPID, wxPayAppConfig.getAppID());
            returnMap.put(Constant.PARTNERID, wxPayAppConfig.getMchID());
            returnMap.put(Constant.PREPAYID, responseMap.get(Constant.PREPAY_ID));
            returnMap.put(Constant.NONCESTR, responseMap.get(Constant.NONCE_STR));
            returnMap.put(Constant.TIMESTAMP, timestamp);
            returnMap.put(Constant.PACKAGE, Constant.SIGN_WXPAY);
            returnMap.put(Constant.SIGN, WXPayUtil.generateSignature(returnMap, wxPayAppConfig.getKey()));//微信支付签名
            return returnMap;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new BaseException(e.getMessage());
        }
    }

    /**
     * 回调
     *
     * @param notifyStr
     * @return String
     * @throws Exception
     * @author SunDawei
     * @date 2019-12-20
     */
    public String notifyUtil(String notifyStr)
    {
        try
        {
            WXPay wxPay = new WXPay(wxPayAppConfig);
            // 转换成map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(notifyStr);
            if (wxPay.isPayResultNotifySignatureValid(resultMap))
            {
                String returnCode = resultMap.get(Constant.RETURN_CODE);  //状态
                String outTradeNo = resultMap.get(Constant.OUT_TRADE_NO);//商户订单号
                String transactionId = resultMap.get(Constant.TRANSACTION_ID);
                if (returnCode.equals(Constant.SUCCESS))
                {
                    if (StringUtils.isNotBlank(outTradeNo))
                    {
                        LOGGER.info("微信手机支付回调成功,订单号:{}", outTradeNo);
                        return outTradeNo;
                    }
                }
            }
            return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new BaseException(e.getMessage());
        }
    }

    public Map<String,String> getRefundResponseMapMap(String orderNo,String refundReason,double amount)
    {
        try
        {
            WXPay wxPay = new WXPay(wxPayAppConfig);
            Map<String, String> requestMap = new HashMap<>();
            requestMap.put(Constant.OUT_TRADE_NO, orderNo);
            requestMap.put(Constant.OUT_REFUND_NO, UUID.randomUUID().toString());
            requestMap.put(Constant.TOTAL_FEE, "订单支付时的总金额，需要从数据库查");
            requestMap.put(Constant.REFUND_FEE, String.valueOf((int) (amount * 100)));//所需退款金额
            requestMap.put(Constant.REFUND_DESC, refundReason);
            return wxPay.refund(requestMap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new BaseException(e.getMessage());
        }
    }
}

