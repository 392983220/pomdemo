package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.ProductInfo;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/19 10:14
 */
public interface ProductService {
    ProductInfo selectGoods(Long productId);

    int queryQuantity(Long productId);

    ProductInfo selectByPrimaryKey(Long productId);

    ProductInfo queryProductByNameAndColor(String productName,String productColor);
}
