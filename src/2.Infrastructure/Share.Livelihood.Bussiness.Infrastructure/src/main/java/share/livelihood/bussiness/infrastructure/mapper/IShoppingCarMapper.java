package share.livelihood.bussiness.infrastructure.mapper;

import share.livelihood.bussiness.domain.core.ShoppingCarDomain;

import java.util.List;
import java.util.Map;

public interface IShoppingCarMapper
{
    Integer addShoppingCar(ShoppingCarDomain shoppingCarDomain);

    void editShoppingCarNum(Map<String, String> map);

    void deleteShoppingCarByID(String carId);

    List<ShoppingCarDomain> getListShoppingCar(String userId);

    ShoppingCarDomain getShoppingCarByPId(String productId);

    ShoppingCarDomain getShoppingCarByPIdUId(String productId, String userId);
}
