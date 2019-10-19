package goal.money.consumerdemo.vo;

import lombok.Data;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/17 11:36
 */
@Data
public class ProductInfoVo {
    private Long productId;
    private Long saleQuantity;
    private String productName;
    private Long listingTime;
    private String productDescribe;
    private Long productQuantity;
    private Integer productPrice;
    private String productCategory;
    private String color;
    private String productImg;
    private Integer productScore;
}
