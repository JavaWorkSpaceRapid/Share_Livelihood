package share.livelihood.bussiness.app.service.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import share.base.exceptions.BaseException;
import share.base.extensions.ListExtension;
import share.base.extensions.ObjectExtension;
import share.base.extensions.StringExtension;
import share.livelihood.bussiness.api.service.certificate.ICertificateService;
import share.livelihood.bussiness.app.mapper.CertificateDicMapper;
import share.livelihood.bussiness.app.mapper.CertificateMapper;
import share.livelihood.bussiness.domain.core.certificate.CertificateDicDomain;
import share.livelihood.bussiness.domain.core.certificate.CertificateDomain;
import share.livelihood.bussiness.domain.core.certificate.info.DicInfo;
import share.livelihood.bussiness.domain.utils.IBaseValue;
import share.livelihood.bussiness.dto.certificate.CertificateDto;
import share.livelihood.bussiness.dto.certificate.CertificateOrderInfoRequest;
import share.livelihood.bussiness.dto.certificate.CertificateOrderInfoResponse;
import share.livelihood.bussiness.dto.certificate.MyCertificateInfoDto;
import share.livelihood.bussiness.dto.certificate.info.SubCertificateOrderInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CertificateServiceImpl implements ICertificateService
{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CertificateMapper mapper;

    @Autowired
    private CertificateDicMapper certificateDicMapper;


    /**
     * 平台发行券
     * 1、发行人-issuers：system
     * 2、持有人-holder：system
     * 3、持有类型-holderType：system
     * 4、是否发行-issue：TRUE
     * 5、是否锁定-locked：FALSE
     *
     * @param count
     * @param certificateDicCode
     * @return
     */
    @Override
    public boolean addCertificates(int count, String certificateDicCode) {
        Query query = new Query(Criteria.where(IBaseValue.certificateDicCode).is(certificateDicCode));
        CertificateDicDomain certificateDicDomain = mongoTemplate.findOne(query, CertificateDicDomain.class);
        long $existsCount = mongoTemplate.count(query, CertificateDomain.class);
        for (int i = IBaseValue.ONE; i <= count; i++) {
            DicInfo randomDicInfo = certificateDicDomain.getRandomDicInfo();
            String strCode = getStrCode(randomDicInfo.getDicCode(), $existsCount + i);
            mongoTemplate.insert(new CertificateDomain(strCode, strCode, randomDicInfo, certificateDicCode, certificateDicDomain.getUnit(), certificateDicDomain.getCount(), certificateDicDomain.getIssuers()));
        }
        return true;
    }

    /**
     * 根据券字典编码和持有人信息获取有效券数据量
     *
     * @param certificateDicCode
     * @param holder
     * @return
     */
    @Override
    public long getCount4Sail(String certificateDicCode, String holder) {
        Query query = new Query(Criteria.where(IBaseValue.certificateDicCode).is(certificateDicCode).
                and(IBaseValue.holder).is(holder).
                and(IBaseValue.issue).is(Boolean.TRUE).
                and(IBaseValue.locked).is(Boolean.FALSE));
        return mongoTemplate.count(query, CertificateDomain.class);
    }

    /**
     * 根据持有人Id获取当前持有人所有的券
     *
     * @param holder
     * @return
     */
    @Override
    public List<MyCertificateInfoDto> getValidCertificate(String holder) {
        List<MyCertificateInfoDto> $return = new ArrayList<>();
        Query query = new Query(this.byHolder4Valid(holder));
        List<CertificateDto> certificateDtos = mapper.asCertificateDto(mongoTemplate.find(query, CertificateDomain.class));
        if (!ListExtension.isNullOrEmpty(certificateDtos)) {
            Map<String, List<CertificateDto>> certificateDtoMap = certificateDtos.stream().collect(Collectors.groupingBy(CertificateDto::getCertificateDicCode));
            Set<String> certificateDicCodes = certificateDtoMap.keySet();
            List<MyCertificateInfoDto> myCertificateInfoDtos = certificateDicMapper.asMyCertificateInfoDto(
                    mongoTemplate.find(new Query(Criteria.where(IBaseValue._id).in(certificateDicCodes)), CertificateDicDomain.class));
            for (MyCertificateInfoDto myCertificateInfoDto : myCertificateInfoDtos) {
                String id = myCertificateInfoDto.getId();
                List<CertificateDto> innerCertificateDto = certificateDtoMap.get(id);
                myCertificateInfoDto.setValidCount((long) innerCertificateDto.size());
//                myCertificateInfoDto.setCertificates(innerCertificateDto);
                $return.add(myCertificateInfoDto);
            }
        }
        return $return;
    }

    @Override
    public long getValidCount(String holder) {
        return mongoTemplate.count(new Query(this.byHolder4Valid(holder)), CertificateDomain.class);
    }

    /**
     * 获取详细券信息
     *
     * @param holder
     * @param certificateDicCode
     * @return
     */
    @Override
    public List<CertificateDto> getDetailValidCertificate(String holder, String certificateDicCode) {
        Criteria criteria = this.byHolder4Valid(holder);
        criteria.and(IBaseValue.certificateDicCode).is(certificateDicCode);
        Query query4Get = new Query(criteria);
        return mapper.asCertificateDto(mongoTemplate.find(query4Get, CertificateDomain.class));
    }


    /**
     * 购买券订单开始时为指定券加锁
     *
     * @param orderId                      订单号
     * @param seller                       商家Id
     * @param certificateOrderInfoRequests
     * @return
     */
    @Override
    public CertificateOrderInfoResponse lock4Sail(String orderId, String seller, List<CertificateOrderInfoRequest> certificateOrderInfoRequests) {
        if (ListExtension.isNullOrEmpty(certificateOrderInfoRequests)) {
            throw new BaseException("券请求信息为空！");
        }
        List<String> certificateDicCodes = certificateOrderInfoRequests.stream().map(CertificateOrderInfoRequest::getCertificateDicCode).collect(Collectors.toList());
        List<CertificateDicDomain> certificateDicDomains = mongoTemplate.find(new Query(Criteria.where(IBaseValue._id).in(certificateDicCodes)), CertificateDicDomain.class);
        if (certificateDicDomains.size() != certificateOrderInfoRequests.size()) {
            throw new BaseException("获取券信息失败！");
        }
        Map<String, Integer> certificateCodeAndCountMap = certificateOrderInfoRequests.stream().collect(Collectors.toMap(CertificateOrderInfoRequest::getCertificateDicCode, CertificateOrderInfoRequest::getCount));
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<SubCertificateOrderInfo> subCertificateOrderInfos = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (CertificateDicDomain certificateDicDomain : certificateDicDomains) {
            String certificateDicCode = certificateDicDomain.getCertificateDicCode();
            Integer count = certificateCodeAndCountMap.get(certificateDicCode);
            if (ObjectExtension.isNull(count)) {
                throw new BaseException("获取券信息失败！");
            }
            /** 根据持有人获取有效（可交易）券 */
            Criteria criteria = this.byHolder4Valid(seller);
            criteria.and(IBaseValue.certificateDicCode).is(certificateDicCode);
            Query query4Get = new Query(criteria).limit(count);
            /** 获取指定持有人中有效的券 */
            List<CertificateDomain> certificateDomains = mongoTemplate.find(query4Get, CertificateDomain.class);
            if (ListExtension.isNullOrEmpty(certificateDomains) || certificateDomains.size() < count) {
                throw new BaseException("券数量不足，请联系平台管理人员！");
            }
            /** 生成有效可交易券Id集合 */
            List<String> $ids = certificateDomains.stream().map(CertificateDomain::getId).collect(Collectors.toList());
            BigDecimal $subTotalPrice = BigDecimal.valueOf(count).multiply(certificateDicDomain.getPrice());
            totalPrice = totalPrice.add($subTotalPrice);
            subCertificateOrderInfos.add(new SubCertificateOrderInfo($subTotalPrice, certificateDicCode, $ids));
            ids.addAll($ids);
        }
        /** 锁定指定券的查询条件 */
        Query query4Update = new Query(Criteria.where(IBaseValue._id).in(ids));
        /** 更新锁定券信息：锁定，添加订单号 */
        Update update = new Update().set(IBaseValue.locked, Boolean.TRUE).set(IBaseValue.orderId, orderId);
        mongoTemplate.updateMulti(query4Update, update, CertificateDomain.class);
        return new CertificateOrderInfoResponse(totalPrice, subCertificateOrderInfos);
    }

    /**
     * 取消购买操作时解锁锁定的券
     *
     * @param orderId
     * @param certificateOrderInfoResponse
     * @return
     */
    @Override
    public boolean cancel4Sail(String orderId, CertificateOrderInfoResponse certificateOrderInfoResponse) {
        Query query4Update = this.getQuery4UpdateByOrderInfo(orderId, certificateOrderInfoResponse);
        Update update = new Update().set(IBaseValue.locked, Boolean.FALSE).set(IBaseValue.orderId, null);
        mongoTemplate.updateMulti(query4Update, update, CertificateDomain.class);
        return true;
    }



    /**
     * 购买券订单完成后为指定券解锁并替换持有人信息
     *
     * @return
     */
    @Override
    public boolean unlock4Sail(String orderId, String buyer, String buyerType, CertificateOrderInfoResponse certificateOrderInfoResponse) {
        Query query4Update = this.getQuery4UpdateByOrderInfo(orderId, certificateOrderInfoResponse);
        /** 初始化持有人类型 */
        String holderType;
        if (StringExtension.isNullOrWhiteSpace(buyerType)) {
            holderType = IBaseValue.person;
        } else {
            holderType = buyerType;
        }
        Update update = new Update().
                set(IBaseValue.locked, Boolean.FALSE).
                set(IBaseValue.orderId, null).
                set(IBaseValue.holder, buyer).
                set(IBaseValue.holderType, holderType);
        mongoTemplate.updateMulti(query4Update, update, CertificateDomain.class);
        return true;
    }


    /**
     * 用券兑换商品订单开始时加锁
     *
     * @return
     */
    @Override
    public CertificateOrderInfoResponse lock4Exchange(String orderId, String seller, boolean autoLock, int count, List<String> ids) {
        List<CertificateDto> certificateDtos;
        if (autoLock) {
            Query query4Get = new Query(this.byHolder4Valid(seller));
            query4Get.limit(count);
            certificateDtos = mapper.asCertificateDto(mongoTemplate.find(query4Get, CertificateDomain.class));
            if (certificateDtos.size() != count) {
                throw new BaseException("券数量与有效券信息不符！");
            }
        } else {
            if (ids.size() != count) {
                throw new BaseException("券数量与券Id信息不符");
            }
            Query query4Get = new Query(this.byHolder4Valid(seller).and(IBaseValue._id).in(ids));
            certificateDtos = mapper.asCertificateDto(mongoTemplate.find(query4Get, CertificateDomain.class));
            if (certificateDtos.size() != count) {
                throw new BaseException("券数量与有效券信息不符！");
            }
        }
        List<SubCertificateOrderInfo> subCertificateOrderInfos = new ArrayList<>();
        Map<String, List<CertificateDto>> certificateMap = certificateDtos.stream().collect(Collectors.groupingBy(CertificateDto::getCertificateDicCode));
        List<String> cerIds = certificateDtos.stream().map(CertificateDto::getId).collect(Collectors.toList());
        for (String certificateDicCode : certificateMap.keySet()) {
            List<String> $ids = certificateMap.get(certificateDicCode).stream().map(CertificateDto::getId).collect(Collectors.toList());
            subCertificateOrderInfos.add(new SubCertificateOrderInfo(null, certificateDicCode, $ids));
        }
        /** 锁定指定券的查询条件 */
        Query query4Update = new Query(Criteria.where(IBaseValue._id).in(cerIds));
        /** 更新锁定券信息：锁定，添加订单号 */
        Update update = new Update().set(IBaseValue.locked, Boolean.TRUE).set(IBaseValue.orderId, orderId);
        mongoTemplate.updateMulti(query4Update, update, CertificateDomain.class);
        return new CertificateOrderInfoResponse(null, subCertificateOrderInfos);
    }

    /**
     * 取消兑换操作时解锁锁定的券
     *
     * @param orderId
     * @param certificateOrderInfoResponse
     * @return
     */
    @Override
    public boolean cancel4Exchange(String orderId, CertificateOrderInfoResponse certificateOrderInfoResponse) {
        return this.cancel4Sail(orderId, certificateOrderInfoResponse);
    }

    /**
     * 用券兑换商品订单完成后为指定券解锁并替换持有人信息
     *
     * @return
     */
    @Override
    public boolean unlock4Exchange(String orderId, String buyer, String buyerType, CertificateOrderInfoResponse certificateOrderInfoResponse) {
        Query query4Update = this.getQuery4UpdateByOrderInfo(orderId, certificateOrderInfoResponse);
        /** 初始化持有人类型 */
        String holderType;
        if (StringExtension.isNullOrWhiteSpace(buyerType)) {
            holderType = IBaseValue.system;
        } else {
            holderType = buyerType;
        }
        /** 更新券信息 */
        Update update = new Update().
                set(IBaseValue.locked, Boolean.FALSE).
                set(IBaseValue.orderId, null).
                set(IBaseValue.holder, buyer).
                set(IBaseValue.holderType, holderType).
                set(IBaseValue.issue, Boolean.FALSE);
        mongoTemplate.updateMulti(query4Update, update, CertificateDomain.class);
        return true;
    }

    private Query getQuery4UpdateByOrderInfo(String orderId, CertificateOrderInfoResponse certificateOrderInfoResponse) {
        List<String> certificateIds = new ArrayList<>();
        certificateOrderInfoResponse.getSubCertificateOrderInfos().forEach(subCertificateOrderInfo -> certificateIds.addAll(subCertificateOrderInfo.getIds()));
        List<String> ids = this.checkAndGetCertificateIds(orderId, certificateIds);
        return new Query(Criteria.where(IBaseValue._id).in(ids));
    }

    private List<String> checkAndGetCertificateIds(String orderId, List<String> certificateIds) {
        Query query4Get = new Query(this.byOrderId(orderId));
        List<CertificateDomain> certificateDomains = mongoTemplate.find(query4Get, CertificateDomain.class);
        if (ListExtension.isNullOrEmpty(certificateDomains)) {
            throw new BaseException("锁定券数量为空！");
        }
        /** 需要解锁的券Id */
        List<String> ids = certificateDomains.stream().map(CertificateDomain::getId).collect(Collectors.toList());
        this.checkContainCertificate(certificateIds, ids);
        return ids;
    }

    /**
     * 校验券信息是否一致
     *
     * @param getIds
     * @param findIds
     */
    public void checkContainCertificate(List<String> getIds, List<String> findIds) {
        boolean flag = false;
        if (getIds.size() != findIds.size()) {
            flag = true;
        }
        for (String findId : findIds) {
            if (!getIds.contains(findId)) {
                flag = true;
            }
        }
        if (flag) {
            throw new BaseException("订单中券与锁定券信息不一致！");
        }
    }

    /**
     * 根据单号获取
     *
     * @param orderId
     * @return
     */
    private Criteria byOrderId(String orderId) {
        return Criteria.where(IBaseValue.orderId).is(orderId);
    }

    /**
     * 根据持有人获取有效（可交易）券
     *
     * @param holder
     * @return
     */
    private Criteria byHolder4Valid(String holder) {
        return Criteria.where(IBaseValue.holder).is(holder).
                and(IBaseValue.issue).is(Boolean.TRUE).
                and(IBaseValue.locked).is(Boolean.FALSE);
    }

    private String getStrCode(String code, long num) {
        String strCode = String.valueOf(num);
        StringBuilder stringBuilder = new StringBuilder(code);
        for (int i = 0; i <= (9 - strCode.length()); i++) {
            stringBuilder.append("0");
        }
        return stringBuilder.append(strCode).toString();
    }

}
