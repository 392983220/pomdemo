package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.ProductInfo;

import java.util.List;

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

    List<ProductInfo> queryProductListByName(String productName,String productCategory);

    List<ProductInfo>  showIndexProductList(String productCategory,int startPage,int pageSize);

    ProductInfo defaultProduct();
}
