package share.livelihood.bussiness.infrastructure.dao.impl;

import livelihood.bussiness.domain.core.ShoppingCarDomain;
import livelihood.bussiness.domain.dao.IShoppingCarDao;
import livelihood.bussiness.infrastructure.mapper.IShoppingCarMapper;
import lycan.base.extensions.StringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author SunDawei
 * @date 2019/11/21 20:26
 */
@Component
public class ShoppingCarDaoImpl implements IShoppingCarDao
{
    @Autowired
    private IShoppingCarMapper iShoppingCarMapper;
    @Override
    public String addShoppingCar(ShoppingCarDomain shoppingCarDomain)
    {
        if(null != shoppingCarDomain
                && !StringExtension.isNullOrWhiteSpace(shoppingCarDomain.getProduct_id()))
        {
            ShoppingCarDomain shoppingCarDomain1 = iShoppingCarMapper.getShoppingCarByPIdUId(shoppingCarDomain.getProduct_id(),shoppingCarDomain.getUser_id());
            if(shoppingCarDomain1!=null)
            {
                Map<String,String> map = new HashMap<>();
                int proCount = 0;
                if(shoppingCarDomain1.getProduct_count() == 0)
                {
                    proCount = 1;
                }
                else
                {
                    proCount = shoppingCarDomain.getProduct_count();
                }
                int count = shoppingCarDomain1.getProduct_count() + proCount;
                map.put("productCount",String.valueOf(count));
                map.put("carId",shoppingCarDomain1.getCar_id());
                iShoppingCarMapper.editShoppingCarNum(map);
            }
            else {
                iShoppingCarMapper.addShoppingCar(shoppingCarDomain);
            }
        }
        return shoppingCarDomain.getCar_id();
    }

    @Override
    public Boolean editShoppingCarNum(int productCount,String carId)
    {
        Map<String,String> map = new HashMap<>();
        map.put("productCount",String.valueOf(productCount));
        map.put("carId",carId);
        iShoppingCarMapper.editShoppingCarNum(map);
        return true;
    }

    @Override
    public Boolean deleteShoppingCarByID(String carId) {
        iShoppingCarMapper.deleteShoppingCarByID(carId);
        return true;
    }

    @Override
    public List<ShoppingCarDomain> getListShoppingCar(String userId) {
        return iShoppingCarMapper.getListShoppingCar(userId);
    }
}
