package share.livelihood.bussiness.api.service.certificate;

import share.livelihood.bussiness.dto.certificate.CertificateDicDto;

import java.util.List;

public interface ICertificateDicService {
    String add(CertificateDicDto certificateDicDto);

    CertificateDicDto getByCode(String code);

    List<CertificateDicDto> getAllValid();
}
