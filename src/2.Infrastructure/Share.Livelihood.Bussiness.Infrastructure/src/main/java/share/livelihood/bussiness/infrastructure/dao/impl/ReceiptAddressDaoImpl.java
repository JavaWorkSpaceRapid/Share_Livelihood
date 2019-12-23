package share.livelihood.bussiness.infrastructure.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import share.livelihood.bussiness.domain.core.ReceiptAddressDomain;
import share.livelihood.bussiness.infrastructure.dao.IReceiptAddressDao;
import share.livelihood.bussiness.infrastructure.mapper.IReceiptAddressMapper;

import java.util.List;

@Component
public class ReceiptAddressDaoImpl implements IReceiptAddressDao
{
    @Autowired
    private IReceiptAddressMapper iReceiptAddressMapper;

    @Override
    public Boolean addReceiptAddress(ReceiptAddressDomain receiptAddressDomain)
    {
        iReceiptAddressMapper.addReceiptAddress(receiptAddressDomain);
        return true;
    }

    @Override
    public Boolean editReceiptAddress(ReceiptAddressDomain receiptAddressDomain)
    {
        iReceiptAddressMapper.editReceiptAddress(receiptAddressDomain);
        return true;
    }

    @Override
    public Boolean delReceiptAddressById(String receiptId)
    {
        iReceiptAddressMapper.delReceiptAddressById(receiptId);
        return true;
    }

    @Override
    public List<ReceiptAddressDomain> getReceiptAddressDomainByUserId(String userId)
    {
        return iReceiptAddressMapper.getReceiptAddressDomainByUserId(userId);
    }

    @Override
    public Boolean setDefaultAddress(String receiptId,String userId)
    {
        iReceiptAddressMapper.setDefaultAddressByUserId(userId);
        iReceiptAddressMapper.setDefaultAddress(receiptId);
        return true;
    }
}
