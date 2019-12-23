package share.livelihood.bussiness.domain.core.order;/**
 * Created by Administrator on 2019/12/3.
 */

import lombok.Data;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/12/3 21:44
 */
@Data
public class OrderProductDomain
{
    //唯一标识
    private String order_product_id;
    //购物车Id
    private String car_id;
    //产品Id
    private String product_id;
    //订单编号
    private String order_id;
    //商品名称
    private String product_name;
    //商品价格
    private double product_price;
    //会员价格
    private double member_price;
    //促销价格
    private double sales_price;
    //商品产地
    private String product_area;
    //产品单位
    private String product_unit;
    //商品图片
    private String img_src;
    //订单商品数量
    private int product_count;
}
