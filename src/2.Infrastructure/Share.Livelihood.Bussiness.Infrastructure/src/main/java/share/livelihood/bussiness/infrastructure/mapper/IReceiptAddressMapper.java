package share.livelihood.bussiness.infrastructure.mapper;

import share.livelihood.bussiness.domain.core.ReceiptAddressDomain;

import java.util.List;

public interface IReceiptAddressMapper
{
    void addReceiptAddress(ReceiptAddressDomain receiptAddressDomain);

    void editReceiptAddress(ReceiptAddressDomain receiptAddressDomain);

    void delReceiptAddressById(String receiptId);

    List<ReceiptAddressDomain> getReceiptAddressDomainByUserId(String userId);

    void setDefaultAddress(String receiptId);

    void setDefaultAddressByUserId(String userId);
}
