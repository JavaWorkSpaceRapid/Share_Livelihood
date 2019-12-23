package share.livelihood.bussiness.domain.core;

import lombok.Data;
import share.domain.EntityBase;

@Data
public class ProductImgInfoDomain extends EntityBase
{
    private String image_id;
    private String product_icon;
    private String product_id;
    private String image_status;
}
