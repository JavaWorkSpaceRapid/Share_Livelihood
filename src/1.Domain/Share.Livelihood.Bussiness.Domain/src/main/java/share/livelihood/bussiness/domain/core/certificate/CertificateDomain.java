package share.livelihood.bussiness.domain.core.certificate;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import share.livelihood.bussiness.domain.core.certificate.info.DicInfo;
import share.livelihood.bussiness.domain.utils.IBaseValue;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
public class CertificateDomain
{
    private String id;
    /**
     * 编码
     */
    private String code;
    /**
     * 券字典编码
     */
    private String certificateDicCode;
    /**
     * 字典信息
     */
    private DicInfo dicInfo;
    /**
     * 发行人
     */
    private String issuers;
    /**
     * 是否发行
     */
    private Boolean issue;
    /**
     * 发行时间
     */
    private Date issueTime;
    /**
     * 持有人
     */
    private String holder;
    /**
     * 持有类型：person：个人、system：系统、company：企业
     */
    private String holderType;
    /**
     * 券单位
     */
    private String unit;
    /**
     * 券单位量
     */
    private Integer count;
    /**
     * 是否锁定
     */
    private Boolean locked;
    /**
     * 订单编码
     */
    private String orderId;

    public CertificateDomain(String id, String code, DicInfo dicInfo, String certificateDicCode, String unit, Integer count, String issuers)
    {
        this.id = id;
        this.code = code;
        this.certificateDicCode = certificateDicCode;
        this.dicInfo = dicInfo;
        this.issuers = issuers;
        this.holder = issuers;
        if (issuers.equals(IBaseValue.system))
        {
            this.holderType = IBaseValue.system;
        }
        else
        {
            this.holderType = IBaseValue.company;
        }
        this.issue = Boolean.TRUE;
        this.issueTime = new Date();
        this.unit = unit;
        this.count = count;
        this.locked = Boolean.FALSE;
    }

}
