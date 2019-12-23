package share.livelihood.bussiness.domain.core.order;/**
 * Created by Administrator on 2019/11/26.
 */

import lombok.Data;
import lycan.domain.EntityBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/26 20:16
 */
@Data
public class OrderInfoDomain extends EntityBase
{
    //订单编号
    private String order_id = getOrderIdByTime();

    //商品类别Id
    private String order_category_id;

    //性别
    private String sex;

    //标签名称
    private String tag_name;

    //省份名称
    private String province_name;

    //城市名称
    private String city_name;

    //区域名称
    private String area_name;

    //收货地址Id
    private String receipt_id;
    //收货人姓名
    private String receipt_name;
    //收货地址
    private String receipt_address;
    //联系电话
    private String receipt_phone;
    //邮编
    private String receipt_postal_code;

    //订单产品列表
    private List<OrderProductDomain> orderProductDomainList;

    //订单价格
    private double order_price;

    //是否正常：0，正常，1，删除
    private String is_show;

    //订单状态：-1(已取消),0（待支付），1（已支付，待发货），2（已发货，待收货），3（已签收）,-2(已删除)
    private String is_status;

    //用户Id
    private String user_id;

    //卖家Id
    private String seller_id;

    //订单描述
    private String order_desc;

    private String getOrderIdByTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++)
        {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
}
