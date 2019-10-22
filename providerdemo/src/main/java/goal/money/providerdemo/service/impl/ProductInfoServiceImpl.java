package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.mapper.ProductInfoMapper;
import goal.money.providerdemo.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @authorzhangshen
 * @date10/22
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;


    @Override
    public List<ProductInfo> getGoods(int productCategory, int startPage, int pageSize) {
        return productInfoMapper.getGoods(productCategory,startPage,pageSize);
    }

    @Override
    public String getDefault() {
        return productInfoMapper.getDefault();
    }

    @Override
    public List<ProductInfo> getAllGoods() {
        return productInfoMapper.getAllGoods();
    }

    @Override
    public ProductInfo selectByProductInfoId(Long id) {
        return productInfoMapper.selectByPrimaryKey(id);
    }

}
