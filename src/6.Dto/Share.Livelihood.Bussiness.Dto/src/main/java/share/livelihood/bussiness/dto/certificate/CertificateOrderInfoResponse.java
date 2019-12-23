package share.livelihood.bussiness.dto.certificate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import share.livelihood.bussiness.dto.certificate.info.SubCertificateOrderInfo;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "CertificateOrderInfoResponse", description = "CertificateOrderInfoResponse")
@NoArgsConstructor
public class CertificateOrderInfoResponse {
    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "SubCertificateOrderInfo")
    private List<SubCertificateOrderInfo> subCertificateOrderInfos;

    public CertificateOrderInfoResponse(BigDecimal totalPrice, List<SubCertificateOrderInfo> subCertificateOrderInfos) {
        this.totalPrice = totalPrice;
        this.subCertificateOrderInfos = subCertificateOrderInfos;
    }
}
