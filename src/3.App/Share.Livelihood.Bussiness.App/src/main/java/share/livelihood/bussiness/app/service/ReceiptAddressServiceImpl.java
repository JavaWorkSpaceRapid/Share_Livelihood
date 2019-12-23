package share.livelihood.bussiness.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.livelihood.bussiness.api.service.IReceiptAddressService;
import share.livelihood.bussiness.domain.core.ReceiptAddressDomain;
import share.livelihood.bussiness.infrastructure.dao.IReceiptAddressDao;

import java.util.List;

@Service
public class ReceiptAddressServiceImpl implements IReceiptAddressService
{
    @Autowired
    private IReceiptAddressDao iReceiptAddressDao;

    @Override
    public Boolean addReceiptAddress(ReceiptAddressDomain receiptAddressDomain)
    {
        return iReceiptAddressDao.addReceiptAddress(receiptAddressDomain);
    }

    @Override
    public Boolean editReceiptAddress(ReceiptAddressDomain receiptAddressDomain)
    {
        return iReceiptAddressDao.editReceiptAddress(receiptAddressDomain);
    }

    @Override
    public Boolean delReceiptAddressById(String receiptId)
    {
        return iReceiptAddressDao.delReceiptAddressById(receiptId);
    }

    @Override
    public List<ReceiptAddressDomain> getReceiptAddressDomainByUserId(String userId)
    {
        return iReceiptAddressDao.getReceiptAddressDomainByUserId(userId);
    }

    @Override
    public Boolean setDefaultAddress(String receiptId,String userId)
    {
        return iReceiptAddressDao.setDefaultAddress(receiptId,userId);
    }
}
