package share.livelihood.bussiness.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class WxPayRequest
{

    /**
     * 平台ID （用于区分平台和商铺）
     */
    private String platformId;

    /**
     * 组织节点ID （用于关联组织节点）
     * Created by zhuzaiji on 2018/12/21
     */
    private String groupId;

    /**
     * 终端IP
     */
    private String spbill_create_ip;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 订单号
     */
    private String out_trade_no;

}
