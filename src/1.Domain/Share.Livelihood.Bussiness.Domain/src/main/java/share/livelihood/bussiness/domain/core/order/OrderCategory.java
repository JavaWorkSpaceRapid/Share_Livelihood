package share.livelihood.bussiness.domain.core.order;

import lombok.Data;
import share.domain.EntityBase;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/29 22:34
 */
@Data
public class OrderCategory extends EntityBase
{
    private String order_category_id;
    private String order_category_name;
}
