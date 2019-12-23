package share.livelihood.bussiness.app.mapper;

import fr.xebia.extras.selma.*;
import share.livelihood.bussiness.domain.core.certificate.CertificateDicDomain;
import share.livelihood.bussiness.dto.certificate.CertificateDicDto;
import share.livelihood.bussiness.dto.certificate.MyCertificateInfoDto;

import java.util.List;

import static fr.xebia.extras.selma.CollectionMappingStrategy.ALLOW_GETTER;

@Mapper(withIgnoreMissing = IgnoreMissing.ALL,
        withCollectionStrategy = ALLOW_GETTER,
        withIoC = IoC.SPRING)
public interface CertificateDicMapper {
    CertificateDicDomain asCertificateDicDomain(CertificateDicDto in);

    List<CertificateDicDomain> asCertificateDicDomain(List<CertificateDicDto> in);

    CertificateDicDto asCertificateDicDto(CertificateDicDomain in);

    List<CertificateDicDto> asCertificateDicDto(List<CertificateDicDomain> in);

    @Maps(withIgnoreFields = {"dicInfos", "price"})
    List<MyCertificateInfoDto> asMyCertificateInfoDto(List<CertificateDicDomain> in);
}
