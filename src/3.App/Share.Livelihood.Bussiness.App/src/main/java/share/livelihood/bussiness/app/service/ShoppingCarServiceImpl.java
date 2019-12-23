package share.livelihood.bussiness.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.livelihood.bussiness.api.service.IShoppingCarService;
import share.livelihood.bussiness.domain.core.ShoppingCarDomain;
import share.livelihood.bussiness.infrastructure.dao.IShoppingCarDao;

import java.util.List;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/21 20:23
 */
@Service
public class ShoppingCarServiceImpl implements IShoppingCarService
{
    @Autowired
    private IShoppingCarDao iShoppingCarDao;

    @Override
    public String addShoppingCar(ShoppingCarDomain shoppingCarDomain) {
        return iShoppingCarDao.addShoppingCar(shoppingCarDomain);
    }

    @Override
    public Boolean editShoppingCarNum(int productCount,String carId) {
        return iShoppingCarDao.editShoppingCarNum(productCount,carId);
    }

    @Override
    public Boolean deleteShoppingCarByID(String carId) {
        return iShoppingCarDao.deleteShoppingCarByID(carId);
    }

    @Override
    public List<ShoppingCarDomain> getListShoppingCar(String userId) {
        return iShoppingCarDao.getListShoppingCar(userId);
    }
}
