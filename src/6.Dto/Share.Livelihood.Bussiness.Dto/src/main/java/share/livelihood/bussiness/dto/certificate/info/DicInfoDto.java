package share.livelihood.bussiness.dto.certificate.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "DicInfoDto", description = "DicInfoDto")
public class DicInfoDto {
    @ApiModelProperty(value = "前缀编码")
    private String dicCode;
    @ApiModelProperty(value = "前缀名称")
    private String dicName;
    @ApiModelProperty(value = "前缀描述")
    private String dicDescription;
    @ApiModelProperty(value = "前缀图片Url")
    private String dicImgUrl;
}
