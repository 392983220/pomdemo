package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.ProductInfo;

import java.util.List;

/**
 * @authorzhangshen
 * @date10/22
 */
public interface ProductInfoService {
    //根据类型，分页查找商品
    List<ProductInfo> getGoods(int productCategory, int startPage, int pageSize);

    //根据销量展示默认商品
    String getDefault();

    //查询所有商品
    List<ProductInfo> getAllGoods();

    //根据pId查询商品
    ProductInfo selectByPrimaryKey(Long productId);

    //根据商品名称模糊查询
    List<ProductInfo> checkByName(String productName,int startPage,int pageSize);


}
