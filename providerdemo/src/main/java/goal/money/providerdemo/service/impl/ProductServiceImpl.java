package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.mapper.ProductInfoMapper;
import goal.money.providerdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Override
    public ProductInfo queryProductByNameAndColor(String productName, String productColor) {
        return productInfoMapper.queryProductByNameAndColor(productName,productColor);
    }

    @Override
    public List<ProductInfo> queryProductListByName(String productName,String productCategory) {
        return productInfoMapper.queryProductListByName(productName,productCategory);
    }

    @Override
    public List<ProductInfo> showIndexProductList(String productCategory,int startPage,int pageSize) {
        return productInfoMapper.showIndexProductList(productCategory,startPage,pageSize);
    }

    @Override
    public ProductInfo defaultProduct() {
        return productInfoMapper.defaultProduct();
    }
}
