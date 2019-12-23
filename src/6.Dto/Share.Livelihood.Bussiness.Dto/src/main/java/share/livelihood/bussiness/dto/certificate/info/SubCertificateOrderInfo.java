package share.livelihood.bussiness.dto.certificate.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "SubCertificateOrderInfo", description = "SubCertificateOrderInfo")
@NoArgsConstructor
public class SubCertificateOrderInfo {
    @ApiModelProperty(value = "单品总价")
    private BigDecimal subTotalPrice;
    @ApiModelProperty(value = "券字典编码")
    private String certificateDicCode;
    @ApiModelProperty(value = "单品总价")
    private List<String> ids;

    public SubCertificateOrderInfo(BigDecimal subTotalPrice, String certificateDicCode, List<String> ids) {
        this.subTotalPrice = subTotalPrice;
        this.certificateDicCode = certificateDicCode;
        this.ids = ids;
    }
}
