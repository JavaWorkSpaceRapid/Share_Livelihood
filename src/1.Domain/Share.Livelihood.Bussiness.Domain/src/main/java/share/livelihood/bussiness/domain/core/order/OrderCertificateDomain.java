package share.livelihood.bussiness.domain.core.order;

import lombok.Data;
import lycan.domain.EntityBase;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/12/5 20:35
 */
@Data
public class OrderCertificateDomain extends EntityBase
{
    private String order_certificate_id;
    private String order_id;
    private String certificateDicCode;
    private String certificateIds;
}
