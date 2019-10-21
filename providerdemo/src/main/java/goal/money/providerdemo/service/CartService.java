package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.CartInfo;

import java.util.List;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/17 12:02
 */
public interface CartService {
    //购物车中添加商品
    int insertSelective(CartInfo cartInfo);
    //从购物车中查找Pid列表
    List<CartInfo> selectCartList(String phone);
    //物理删除购物车信息
    int deleteByPrimaryKey(Long cartId);

    void updatePriceMultiplyQuantity(Long cartId);

    CartInfo queryCart(String phone,Long productId);

    void updateBuyQuantity(int buyQuantity,long cartId);

    CartInfo queryCartById(Long cartId);

    CartInfo selectByPrimaryKey(Long cartId);
}
