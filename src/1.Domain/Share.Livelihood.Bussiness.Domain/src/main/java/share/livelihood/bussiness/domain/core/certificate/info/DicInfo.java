package share.livelihood.bussiness.domain.core.certificate.info;

import lombok.Data;

@Data
public class DicInfo {
    /**
     * 前缀编码
     */
    private String dicCode;
    /**
     * 前缀名称
     */
    private String dicName;
    /**
     * 前缀描述
     */
    private String dicDescription;
    /**
     * 前缀图片Url
     */
    private String dicImgUrl;
}
