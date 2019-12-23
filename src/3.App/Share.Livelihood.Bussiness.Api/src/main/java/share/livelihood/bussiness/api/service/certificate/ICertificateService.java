package share.livelihood.bussiness.api.service.certificate;


import share.livelihood.bussiness.dto.certificate.CertificateDto;
import share.livelihood.bussiness.dto.certificate.CertificateOrderInfoRequest;
import share.livelihood.bussiness.dto.certificate.CertificateOrderInfoResponse;
import share.livelihood.bussiness.dto.certificate.MyCertificateInfoDto;

import java.util.List;

public interface ICertificateService {

    boolean addCertificates(int count, String certificateDicCode);

    long getCount4Sail(String certificateDicCode, String holder);

    List<MyCertificateInfoDto> getValidCertificate(String holder);

    long getValidCount(String holder);

    List<CertificateDto> getDetailValidCertificate(String holder, String certificateDicCode);

    CertificateOrderInfoResponse lock4Sail(String orderId, String seller, List<CertificateOrderInfoRequest> certificateOrderInfoRequests);

    boolean cancel4Sail(String orderId, CertificateOrderInfoResponse certificateOrderInfoResponse);

    boolean unlock4Sail(String orderId, String buyer, String buyerType, CertificateOrderInfoResponse certificateOrderInfoResponse);

    CertificateOrderInfoResponse lock4Exchange(String orderId, String seller, boolean autoLock, int count, List<String> ids);

    boolean cancel4Exchange(String orderId, CertificateOrderInfoResponse certificateOrderInfoResponse);

    boolean unlock4Exchange(String orderId, String buyer, String buyerType, CertificateOrderInfoResponse certificateOrderInfoResponse);
}
