package share.livelihood.bussiness.api.service;

import share.livelihood.bussiness.domain.core.AdvertisingDomain;

import java.util.List;

public interface IAdvertisingService
{
    String addAdvertising(AdvertisingDomain advertisingDomain);

    Boolean updateAdvertising(AdvertisingDomain advertisingDomain);

    Boolean deleteAdvertising(String advertId);

    List<AdvertisingDomain> getAdvertising();
}
