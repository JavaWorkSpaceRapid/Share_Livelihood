package share.livelihood.bussiness.dto.certificate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import share.livelihood.bussiness.dto.certificate.info.DicInfoDto;

@Data
@ApiModel(value = "CertificateDto", description = "CertificateDto")
public class CertificateDto {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "编码")
    private String code;
    @ApiModelProperty(value = "券字典编码")
    private String certificateDicCode;
    @ApiModelProperty(value = "字典信息")
    private DicInfoDto dicInfo;
    @ApiModelProperty(value = "券单位")
    private String unit;
    @ApiModelProperty(value = "券单位量")
    private Integer count;
}
