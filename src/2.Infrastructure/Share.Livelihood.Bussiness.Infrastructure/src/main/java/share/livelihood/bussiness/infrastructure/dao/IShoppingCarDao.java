package share.livelihood.bussiness.infrastructure.dao;

import share.livelihood.bussiness.domain.core.ShoppingCarDomain;

import java.util.List;

public interface IShoppingCarDao
{
    String addShoppingCar(ShoppingCarDomain shoppingCarDomain);

    Boolean editShoppingCarNum(int productCount, String carId);

    Boolean deleteShoppingCarByID(String carId);

    List<ShoppingCarDomain> getListShoppingCar(String userId);
}
