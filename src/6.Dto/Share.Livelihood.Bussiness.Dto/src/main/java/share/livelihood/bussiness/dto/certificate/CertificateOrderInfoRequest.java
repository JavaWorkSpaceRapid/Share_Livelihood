package share.livelihood.bussiness.dto.certificate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CertificateOrderInfoRequest", description = "CertificateOrderInfoRequest")
public class CertificateOrderInfoRequest {
    @ApiModelProperty(value = "券类别编码")
    String certificateDicCode;
    @ApiModelProperty(value = "券数量")
    int count;
}
