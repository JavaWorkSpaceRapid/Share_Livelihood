package share.livelihood.bussiness.infrastructure.dao.impl;

import livelihood.bussiness.domain.core.AdvertisingDomain;
import livelihood.bussiness.domain.dao.IAdvertisingDao;
import livelihood.bussiness.infrastructure.mapper.IAdvertisingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/14 20:03
 */
@Component
public class AdvertisingDaoImpl implements IAdvertisingDao
{
    @Autowired
    private IAdvertisingMapper iAdvertisingMapper;

    @Override
    public String addAdvertising(AdvertisingDomain advertisingDomain)
    {
        return String.valueOf(iAdvertisingMapper.addAdvertising(advertisingDomain));
    }

    @Override
    public void updateAdvertising(AdvertisingDomain advertisingDomain)
    {
        iAdvertisingMapper.updateAdvertising(advertisingDomain);
    }

    @Override
    public void deleteAdvertising(String advertId)
    {
        iAdvertisingMapper.deleteAdvertising(advertId);
    }

    @Override
    public List<AdvertisingDomain> getAdvertising()
    {
        return iAdvertisingMapper.getAdvertising();
    }
}
