package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.CartInfo;

import java.util.List;

/**
 * @authorwxf
 * @date10/22
 */
public interface CartInfoService {
    //添加购物车
    int insertSelective(CartInfo cartInfo);

    //查询购物车商品数量
    List<CartInfo> queryCartByPhone(String phone);

}
