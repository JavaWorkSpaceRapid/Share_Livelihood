package share.livelihood.bussiness.domain.core;

import lombok.Data;
import share.domain.EntityBase;

/**
 * Created by SunDawei on 2019/8/10.
 */
@Data
public class CategoryInfoDomain extends EntityBase
{
    private String category_id;
    private String merchant_id;
    private String boiler_id;
    private String category_name;
    private String category_icon;
    private String category_desc;
    private String parent_id;
}
