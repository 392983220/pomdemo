package goal.money.consumerdemo.vo;

import java.util.Map;

/**
 * @authorZhouWeiPing
 * @date10/22
 */
public class NameAndUrl {
    private  Map<Integer,String> productCategory;

    public Map<Integer, String> getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Map<Integer, String> productCategory) {
        productCategory.put(1,"苹果");
        productCategory.put(2,"橘子");
        this.productCategory = productCategory;
    }
}
