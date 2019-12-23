package share.livelihood.bussiness.app.service.certificate;

import livelihood.bussiness.api.service.certificate.ICertificateDicService;
import livelihood.bussiness.api.service.certificate.ICertificateService;
import livelihood.bussiness.app.mapper.CertificateDicMapper;
import livelihood.bussiness.domain.core.certificate.CertificateDicDomain;
import livelihood.bussiness.domain.utils.IBaseValue;
import livelihood.bussiness.dto.certificate.CertificateDicDto;
import lycan.base.extensions.ListExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateDicServiceImpl implements ICertificateDicService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CertificateDicMapper mapper;

    @Autowired
    private ICertificateService certificateService;

    @Override
    public String add(CertificateDicDto certificateDicDto) {
        CertificateDicDomain certificateDicDomain = mapper.asCertificateDicDomain(certificateDicDto);
        return mongoTemplate.save(certificateDicDomain).getId();
    }

    @Override
    public CertificateDicDto getByCode(String code) {
        CertificateDicDomain certificateDicCode = mongoTemplate.findOne(new Query(Criteria.where(IBaseValue._id).is(code)), CertificateDicDomain.class);
        return mapper.asCertificateDicDto(certificateDicCode);
    }

    @Override
    public List<CertificateDicDto> getAllValid() {
        List<CertificateDicDto> issue = mapper.asCertificateDicDto(mongoTemplate.find(new Query(Criteria.where(IBaseValue.issue).is(Boolean.TRUE)), CertificateDicDomain.class));
        if (!ListExtension.isNullOrEmpty(issue)) {
            issue.forEach(certificateDicDto -> {
                certificateDicDto.setValidCount(certificateService.getCount4Sail(certificateDicDto.getCertificateDicCode(), certificateDicDto.getIssuers()));
                certificateDicDto.setDicInfos(null);
            });
        }
        return issue;
    }
}
