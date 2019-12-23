package share.livelihood.bussiness.domain.core;

import lombok.Data;
import share.domain.EntityBase;

@Data
public class ReceiptAddressDomain extends EntityBase
{
    private String receipt_id;
    private String user_id;
    private String sex;
    private String tag_name;
    private String province_name;
    private String city_name;
    private String area_name;
    private String receipt_name;
    private String receipt_phone;
    private String receipt_address;
    private String receipt_postal_code;
    /**
     * 1,默認收穫地址
     */
    private String is_status;
}
