package share.livelihood.bussiness.infrastructure.dao;


import share.livelihood.bussiness.domain.core.AdvertisingDomain;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/14 20:00
 */
public interface IAdvertisingDao
{
    String addAdvertising(AdvertisingDomain advertisingDomain);

    void updateAdvertising(AdvertisingDomain advertisingDomain);

    void deleteAdvertising(String advertId);

    List<AdvertisingDomain> getAdvertising();
}
