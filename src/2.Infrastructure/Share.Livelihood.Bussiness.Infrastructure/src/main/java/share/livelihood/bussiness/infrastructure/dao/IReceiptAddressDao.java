package share.livelihood.bussiness.infrastructure.dao;


import share.livelihood.bussiness.domain.core.ReceiptAddressDomain;

import java.util.List;

public interface IReceiptAddressDao
{
    Boolean addReceiptAddress(ReceiptAddressDomain receiptAddressDomain);

    Boolean editReceiptAddress(ReceiptAddressDomain receiptAddressDomain);

    Boolean delReceiptAddressById(String receiptId);

    List<ReceiptAddressDomain> getReceiptAddressDomainByUserId(String userId);

    Boolean setDefaultAddress(String receiptId, String userId);
}
