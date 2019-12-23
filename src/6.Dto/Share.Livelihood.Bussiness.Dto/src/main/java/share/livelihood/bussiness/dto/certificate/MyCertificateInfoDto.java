package share.livelihood.bussiness.dto.certificate;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "MyCertificateInfoDto", description = "MyCertificateInfoDto")
public class MyCertificateInfoDto extends CertificateDicDto {
    List<CertificateDto> certificates;
}
