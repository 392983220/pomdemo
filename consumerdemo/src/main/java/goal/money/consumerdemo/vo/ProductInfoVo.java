package goal.money.consumerdemo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/17 11:36
 */
@Data
public class ProductInfoVo implements Serializable {
    private Long productId;
    private String productName;
    private String productDescribe;
    private Integer productPrice;
    private int productCategory;
    private String productColor;
    private String productImg;
    private Integer productScore;
    private Integer buyQuantity;
}
