package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.CartInfo;
import goal.money.providerdemo.mapper.CartInfoMapper;
import goal.money.providerdemo.mapper.ProductInfoMapper;
import goal.money.providerdemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/17 12:02
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartInfoMapper cartInfoMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;


    @Override
    public int insertSelective(CartInfo cartInfo) {
        return cartInfoMapper.insertSelective(cartInfo);
    }

    @Override
    public List<CartInfo> selectCartList(Long userId) {
        return cartInfoMapper.selectCartList(userId);
    }

    @Override
    public int deleteByPrimaryKey(Long cartId) {
        return cartInfoMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public void updatePriceMultiplyQuantity(Long cartId) {
        cartInfoMapper.updatePriceMultiplyQuantity(cartId);
    }

    @Override
    public CartInfo queryCart(Long userId, Long productId) {
        return cartInfoMapper.queryCart(userId,productId);
    }

    @Override
    public void updateBuyQuantity(int buyQuantity) {
        cartInfoMapper.updateBuyQuantity(buyQuantity);
    }

    @Override
    public CartInfo queryCartById(Long cartId) {
        return cartInfoMapper.queryCartById(cartId);
    }


}
