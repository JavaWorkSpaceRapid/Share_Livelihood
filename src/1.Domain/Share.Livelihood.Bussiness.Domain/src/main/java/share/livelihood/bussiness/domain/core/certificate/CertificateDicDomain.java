package share.livelihood.bussiness.domain.core.certificate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import share.livelihood.bussiness.domain.core.certificate.info.DicInfo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Document
@NoArgsConstructor
public class CertificateDicDomain {
    @Getter
    /** 券字典Id */
    private String id;

    @Getter
    /** 券字典编码 */
    private String certificateDicCode;

    @Getter
    @Setter
    /** 券字典名称 */
    private String certificateDicName;

    @Getter
    @Setter
    /** 券字典描述 */
    private String certificateDicDescription;

    @Getter
    @Setter
    /** 券字典图片 */
    private String certificateDicImgUrl;

    @Getter
    /** 字典信息量 */
    private int dicInfoCount;

    @Getter
    /** 券字典信息 */
    private List<DicInfo> dicInfos;

    @Getter
    /** 券字典信息字典 */
    private Map<Integer, DicInfo> dicInfoMap;

    @Getter
    @Setter
    /** 券单位量 */
    private Integer count;

    @Getter
    @Setter
    /** 券单位 */
    private String unit;

    @Getter
    @Setter
    /** 券单价 */
    private BigDecimal price;

    @Getter
    @Setter
    /** 发行人 */
    private String issuers;

    @Getter
    @Setter
    /** 是否发行 */
    private Boolean issue;

    public DicInfo getRandomDicInfo() {
        Random random = new Random();
        int a = random.nextInt(dicInfoCount * 1000) % dicInfoCount;
        return dicInfoMap.get(a);
    }

    public void setDicInfos(List<DicInfo> dicInfos) {
        if (dicInfos != null && !dicInfos.isEmpty()) {
            this.dicInfos = dicInfos;
            this.dicInfoCount = dicInfos.size();
            this.dicInfoMap = new LinkedHashMap<>();
            for (int i = 0; i < dicInfos.size(); i++) {
                dicInfoMap.put(i, dicInfos.get(i));
            }
        }
    }

    public void setCertificateDicCode(String certificateDicCode) {
        this.certificateDicCode = certificateDicCode;
        this.id = certificateDicCode;
    }
}
