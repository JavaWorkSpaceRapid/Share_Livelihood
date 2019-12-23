package share.livelihood.bussiness.controller.certificate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import livelihood.bussiness.api.service.certificate.ICertificateDicService;
import livelihood.bussiness.dto.certificate.CertificateDicDto;
import lycan.base.annotation.NoAuth;
import lycan.base.result.LiveResult;
import lycan.web.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/CertificateDic")
@Api(value = "/livelihood/CertificateDic", description = "基础券类型管理")
public class CertificateDicController extends ApiController {

    @Autowired
    private ICertificateDicService service;


    @RequestMapping(value = "/addCertificateDic", method = RequestMethod.POST)
    @ApiOperation(value = "添加字典", notes = "添加字典")
    @NoAuth
    public LiveResult<String> addCertificateDic(@RequestBody CertificateDicDto certificateDicDto) {
        return ok(service.add(certificateDicDto));
    }

    @RequestMapping(value = "/getCertificateDicByCode", method = RequestMethod.GET)
    @ApiOperation(value = "根据编码获取字典", notes = "根据编码获取字典")
    @NoAuth
    public LiveResult<CertificateDicDto> getCertificateDicByCode(@RequestParam String code) {
        return ok(service.getByCode(code));
    }

    @RequestMapping(value = "/getCertificate4Sail", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有在销售的券类别", notes = "获取所有在销售的券类别")
    @NoAuth
    public LiveResult<List<CertificateDicDto>> getCertificate4Sail() {
        return ok(service.getAllValid());
    }
}
