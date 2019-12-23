package share.livelihood.bussiness.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.base.annotation.NoAuth;
import share.base.result.LiveResult;
import share.livelihood.bussiness.api.service.IReceiptAddressService;
import share.livelihood.bussiness.domain.core.ReceiptAddressDomain;
import share.web.ApiController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/livelihood/ReceiptAddress")
@Api(value = "/livelihood/ReceiptAddress", description = "收貨地址")
public class ReceiptAddressController extends ApiController
{
    @Autowired
    private IReceiptAddressService iReceiptAddressService;

    @RequestMapping(value = "/addReceiptAddress", method = RequestMethod.POST)
    @ApiOperation(value = "新增收穫地址", notes = "新增收穫地址")
    @NoAuth
    public LiveResult<Boolean> addReceiptAddress(@RequestBody ReceiptAddressDomain receiptAddressDomain)
    {
        return ok(iReceiptAddressService.addReceiptAddress(receiptAddressDomain));
    }

    @RequestMapping(value = "/editReceiptAddress", method = RequestMethod.POST)
    @ApiOperation(value = "編輯收穫地址", notes = "編輯收穫地址")
    @NoAuth
    public LiveResult<Boolean> editReceiptAddress(@RequestBody ReceiptAddressDomain receiptAddressDomain)
    {
        return ok(iReceiptAddressService.editReceiptAddress(receiptAddressDomain));
    }

    @RequestMapping(value = "/delReceiptAddressById", method = RequestMethod.GET)
    @ApiOperation(value = "刪除收穫地址", notes = "刪除收穫地址")
    @NoAuth
    public LiveResult<Boolean> delReceiptAddressById(@RequestParam(name = "receiptId") String receiptId)
    {
        return ok(iReceiptAddressService.delReceiptAddressById(receiptId));
    }

    @RequestMapping(value = "/getReceiptAddressDomainByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "獲取當前用戶的地址", notes = "獲取當前用戶的地址")
    @NoAuth
    public LiveResult<List<ReceiptAddressDomain>> getReceiptAddressDomainByUserId(@RequestParam(name = "userId") String userId)
    {
        return ok(iReceiptAddressService.getReceiptAddressDomainByUserId(userId));
    }

    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.GET)
    @ApiOperation(value = "設置默認地址", notes = "設置默認地址")
    @NoAuth
    public LiveResult<Boolean> setDefaultAddress(@RequestParam(name = "receiptId") String receiptId,
                                                 @RequestParam(name = "userId") String userId)
    {
        return ok(iReceiptAddressService.setDefaultAddress(receiptId,userId));
    }
}
