package share.livelihood.bussiness.app.utils;

public interface Constant
{
    String GET_PAY_FAIL = "获取预支付交易会话标识失败";

    String WECHAT_PAY_ORDER_FAIL = "微信支付统一下单失败";

    String ORDER_NO_NOT_NULL = "订单编号不能为空";

    String BACK_MONEY_MUST_GIT_ZEOR = "退款金额必须大于0";

    String REFUND_ALIPAY_SUCCESS= "退款申请成功";

    String ORDER_UNUSUAL_MSG="订单异常";

    String XML_BACK_FAIL = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml> ";

    String XML_BACK_SUCCESS = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

    String OUT_TRADE_NO = "out_trade_no";

    String OUT_REFUND_NO = "out_refund_no";

    String TOTAL_FEE = "total_fee";

    String REFUND_FEE = "refund_fee";

    String REFUND_DESC = "refund_desc";

    String RETURN_CODE = "return_code";

    String RESULT_CODE = "result_code";

    String RETURN_MSG = "return_msg";

    String ERR_CODE_DES = "err_code_des";

    String SUCCESS = "SUCCESS";

    String TRANSACTION_ID = "transaction_id";

    String DATA = "data";

    String BODY = "body";

    String SPBILL_CREATE_IP = "spbill_create_ip";

    String TRADE_TYPE = "trade_type";

    String NOTIFY_URL = "notify_url";

    String TRADE_TYPE_JSAPI = "JSAPI";

    String APPID = "appid";

    String PARTNERID ="partnerid";

    String PREPAYID = "prepayid";

    String PREPAY_ID = "prepay_id";

    String NONCESTR = "noncestr";

    String NONCE_STR = "nonce_str";

    String TIMESTAMP = "timestamp";

    String PACKAGE ="package";

    String SIGN = "sign";

    String SIGN_WXPAY = "Sign=WXPay";
}
