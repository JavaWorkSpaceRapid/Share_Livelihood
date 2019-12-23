package share.livelihood.bussiness.domain.core;

import lombok.Data;
import share.domain.EntityBase;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/14 19:55
 */
@Data
public class AdvertisingDomain extends EntityBase
{
    private String advert_id;
    private String title;
    private String picture;
    private String content;
    private String isStatus;
}
