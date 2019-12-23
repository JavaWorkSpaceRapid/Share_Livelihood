package share.livelihood.bussiness.api.service;

import share.livelihood.bussiness.domain.core.ShoppingCarDomain;

import java.util.List;

public interface IShoppingCarService
{
    String addShoppingCar(ShoppingCarDomain shoppingCarDomain);

    Boolean editShoppingCarNum(int productCount, String carId);

    Boolean deleteShoppingCarByID(String carId);

    List<ShoppingCarDomain> getListShoppingCar(String userId);
}
