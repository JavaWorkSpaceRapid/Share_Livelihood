package share.livelihood.bussiness.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.livelihood.bussiness.api.service.IAdvertisingService;
import share.livelihood.bussiness.domain.core.AdvertisingDomain;
import share.livelihood.bussiness.infrastructure.dao.IAdvertisingDao;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/14 20:01
 */
@Service
public class AdvertisingServiceImpl implements IAdvertisingService
{
    @Autowired
    private IAdvertisingDao iAdvertisingDao;

    @Override
    public String addAdvertising(AdvertisingDomain advertisingDomain)
    {
        return iAdvertisingDao.addAdvertising(advertisingDomain);
    }

    @Override
    public Boolean updateAdvertising(AdvertisingDomain advertisingDomain)
    {
        iAdvertisingDao.updateAdvertising(advertisingDomain);
        return true;
    }

    @Override
    public Boolean deleteAdvertising(String advertId)
    {
        iAdvertisingDao.deleteAdvertising(advertId);
        return true;
    }

    @Override
    public List<AdvertisingDomain> getAdvertising()
    {
        return iAdvertisingDao.getAdvertising();
    }
}
