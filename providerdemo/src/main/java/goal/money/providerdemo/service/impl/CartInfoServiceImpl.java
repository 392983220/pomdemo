package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.CartInfo;
import goal.money.providerdemo.mapper.CartInfoMapper;
import goal.money.providerdemo.service.CartInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @authorwxf
 * @date10/22
 */
@Service
public class CartInfoServiceImpl implements CartInfoService {
    @Autowired
    private CartInfoMapper cartInfoMapper;
    @Override
    public int insertSelective(CartInfo cartInfo) {
        return cartInfoMapper.insertSelective(cartInfo);
    }

    @Override
    public List<CartInfo> queryCartByPhone(String phone) {
        return cartInfoMapper.queryCartByPhone(phone);
    }
}
