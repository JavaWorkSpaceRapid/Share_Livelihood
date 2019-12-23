package share.livelihood.bussiness.domain.core;

import lombok.Data;
import share.domain.EntityBase;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/21 20:19
 */
@Data
public class ShoppingCarDomain extends EntityBase
{
    private String car_id;
    private String seller_id;
    private String user_id;
    private String product_id;
    private String product_name;
    private int product_num;
    private int product_count;
    private double product_price;
    private String member_price;
    private String sales_price;
    private String product_area;
    private String img_src;

    private String product_desc;

    private int one_jin;
    /**
     * 单位
     */
    private String product_unit;
}
