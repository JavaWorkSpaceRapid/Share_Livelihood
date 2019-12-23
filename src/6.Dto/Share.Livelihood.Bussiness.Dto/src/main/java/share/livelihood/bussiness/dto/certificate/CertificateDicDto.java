package share.livelihood.bussiness.dto.certificate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import share.livelihood.bussiness.dto.certificate.info.DicInfoDto;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "CertificateDicDto", description = "CertificateDicDto")
public class CertificateDicDto {
    @ApiModelProperty(value = "券字典Id")
    private String id;
    @ApiModelProperty(value = "券字典编码")
    private String certificateDicCode;
    @ApiModelProperty(value = "券字典名称")
    private String certificateDicName;
    @ApiModelProperty(value = "券字典描述")
    private String certificateDicDescription;
    @ApiModelProperty(value = "券字典图片")
    private String certificateDicImgUrl;
    @ApiModelProperty(value = "券字典信息")
    private List<DicInfoDto> dicInfos;
    @ApiModelProperty(value = "券单位量")
    private Integer count;
    @ApiModelProperty(value = "券单位")
    private String unit;
    @ApiModelProperty(value = "券单价")
    private BigDecimal price;
    @ApiModelProperty(value = "发行人")
    private String issuers;
    @ApiModelProperty(value = "是否发行")
    private Boolean issue;
    @ApiModelProperty(value = "有效库存")
    private Long validCount = 0l;

}
