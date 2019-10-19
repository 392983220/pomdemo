package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.mapper.ProductInfoMapper;
import goal.money.providerdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/19 10:14
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public ProductInfo selectGoods(Long productId) {
        return productInfoMapper.selectGoods(productId);
    }

    @Override
    public int queryQuantity(Long productId) {
        return productInfoMapper.queryQuantity(productId);
    }

    @Override
    public ProductInfo selectByPrimaryKey(Long productId) {
        return productInfoMapper.selectByPrimaryKey(productId);
    }
}
