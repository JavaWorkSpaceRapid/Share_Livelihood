package share.livelihood.bussiness.controller.certificate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import livelihood.bussiness.api.service.certificate.ICertificateService;
import livelihood.bussiness.dto.certificate.CertificateDto;
import livelihood.bussiness.dto.certificate.CertificateOrderInfoRequest;
import livelihood.bussiness.dto.certificate.CertificateOrderInfoResponse;
import livelihood.bussiness.dto.certificate.MyCertificateInfoDto;
import lycan.base.annotation.NoAuth;
import lycan.base.result.LiveResult;
import lycan.web.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/Certificate")
@Api(value = "/livelihood/Certificate", description = "券操作")
public class CertificateController extends ApiController {

    @Autowired
    private ICertificateService service;

    @RequestMapping(value = "/addCertificates", method = RequestMethod.POST)
    @ApiOperation(value = "添加券", notes = "添加券")
    @NoAuth
    public LiveResult<Boolean> addSystemCertificates(@RequestParam int count,
                                                     @RequestParam String certificateDicCode) {
        return ok(service.addCertificates(count, certificateDicCode));
    }

    @RequestMapping(value = "/getMyValidCertificates", method = RequestMethod.GET)
    @ApiOperation(value = "获取我的券（有效）", notes = "获取我的券（有效）")
    @NoAuth
    public LiveResult<List<MyCertificateInfoDto>> getMyValidCertificates(@RequestParam String holder) {
        return ok(service.getValidCertificate(holder));
    }

    @RequestMapping(value = "/getMyValidTotalCount", method = RequestMethod.GET)
    @ApiOperation(value = "获取我的券总量（有效）", notes = "获取我的券总量（有效）")
    @NoAuth
    public LiveResult<Long> getMyValidTotalCount(@RequestParam String holder) {
        return ok(service.getValidCount(holder));
    }

    @RequestMapping(value = "/getMyDetailValidCertificate", method = RequestMethod.GET)
    @ApiOperation(value = "获取我的券详情（有效）", notes = "获取我的券详情（有效）")
    @NoAuth
    public LiveResult<List<CertificateDto>> getMyDetailValidCertificate(@RequestParam String holder,
                                                                        @RequestParam String certificateDicCode) {
        return ok(service.getDetailValidCertificate(holder, certificateDicCode));
    }

    @RequestMapping(value = "/local4Sail", method = RequestMethod.POST)
    @ApiOperation(value = "券销售锁定接口", notes = "券销售锁定接口")
    @NoAuth
    public LiveResult<CertificateOrderInfoResponse> local4Sail(@RequestParam String orderId,
                                                               @RequestParam String seller,
                                                               @RequestBody List<CertificateOrderInfoRequest> certificateOrderInfoRequests) {
        return ok(service.lock4Sail(orderId, seller, certificateOrderInfoRequests));
    }

    @RequestMapping(value = "/cancel4Sail", method = RequestMethod.POST)
    @ApiOperation(value = "取消购买操作时解锁锁定的券", notes = "取消购买操作时解锁锁定的券")
    @NoAuth
    public LiveResult<Boolean> cancel4Sail(@RequestParam String orderId,
                                           @RequestBody CertificateOrderInfoResponse certificateOrderInfoResponse) {
        return ok(service.cancel4Sail(orderId, certificateOrderInfoResponse));
    }

    @RequestMapping(value = "/unlock4Sail", method = RequestMethod.POST)
    @ApiOperation(value = "券销售解锁接口", notes = "券销售解锁接口")
    @NoAuth
    public LiveResult<Boolean> unlock4Sail(@RequestParam String orderId,
                                           @RequestParam String buyer,
                                           @RequestParam String buyerType,
                                           @RequestBody CertificateOrderInfoResponse certificateOrderInfoResponse) {
        return ok(service.unlock4Sail(orderId, buyer, buyerType, certificateOrderInfoResponse));
    }

    @RequestMapping(value = "/lock4Exchange", method = RequestMethod.POST)
    @ApiOperation(value = "券兑换锁定接口", notes = "券兑换锁定接口")
    @NoAuth
    public LiveResult<CertificateOrderInfoResponse> lock4Exchange(@RequestParam String orderId,
                                                                  @RequestParam String seller,
                                                                  @RequestParam boolean autoLock,
                                                                  @RequestParam int count,
                                                                  @RequestBody List<String> ids) {
        return ok(service.lock4Exchange(orderId, seller, autoLock, count, ids));
    }

    @RequestMapping(value = "/cancel4Exchange", method = RequestMethod.POST)
    @ApiOperation(value = "取消兑换操作时解锁锁定的券", notes = "取消兑换操作时解锁锁定的券")
    @NoAuth
    public LiveResult<Boolean> cancel4Exchange(@RequestParam String orderId,
                                               @RequestBody CertificateOrderInfoResponse certificateOrderInfoResponse) {
        return ok(service.cancel4Exchange(orderId, certificateOrderInfoResponse));
    }

    @RequestMapping(value = "/unlock4Exchange", method = RequestMethod.POST)
    @ApiOperation(value = "券兑换解锁接口", notes = "券兑换解锁接口")
    @NoAuth
    public LiveResult<Boolean> unlock4Exchange(@RequestParam String orderId,
                                               @RequestParam String buyer,
                                               @RequestParam String buyerType,
                                               @RequestBody CertificateOrderInfoResponse certificateOrderInfoResponse) {
        return ok(service.unlock4Exchange(orderId, buyer, buyerType, certificateOrderInfoResponse));
    }
}
