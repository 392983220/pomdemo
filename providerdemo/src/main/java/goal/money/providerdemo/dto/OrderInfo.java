package goal.money.providerdemo.dto;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table order_info
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class OrderInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_id
     *
     * @mbg.generated
     */
    private Long orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_number
     *
     * @mbg.generated
     */
    private String orderNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.product_name
     *
     * @mbg.generated
     */
    private String productName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.bought_time
     *
     * @mbg.generated
     */
    private Long boughtTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.product_price
     *
     * @mbg.generated
     */
    private Integer productPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.buy_quantity
     *
     * @mbg.generated
     */
    private Integer buyQuantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_assessment
     *
     * @mbg.generated
     */
    private Integer orderAssessment;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.order_state
     *
     * @mbg.generated
     */
    private Integer orderState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.product_img
     *
     * @mbg.generated
     */
    private String productImg;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.product_id
     *
     * @mbg.generated
     */
    private Long productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.take_delivery_name
     *
     * @mbg.generated
     */
    private String takeDeliveryName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.take_delivery_address
     *
     * @mbg.generated
     */
    private String takeDeliveryAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.take_delivery_phone
     *
     * @mbg.generated
     */
    private Integer takeDeliveryPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.user_phone
     *
     * @mbg.generated
     */
    private Integer userPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_info.product_color
     *
     * @mbg.generated
     */
    private String productColor;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_id
     *
     * @return the value of order_info.order_id
     *
     * @mbg.generated
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_id
     *
     * @param orderId the value for order_info.order_id
     *
     * @mbg.generated
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_number
     *
     * @return the value of order_info.order_number
     *
     * @mbg.generated
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_number
     *
     * @param orderNumber the value for order_info.order_number
     *
     * @mbg.generated
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.product_name
     *
     * @return the value of order_info.product_name
     *
     * @mbg.generated
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.product_name
     *
     * @param productName the value for order_info.product_name
     *
     * @mbg.generated
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.bought_time
     *
     * @return the value of order_info.bought_time
     *
     * @mbg.generated
     */
    public Long getBoughtTime() {
        return boughtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.bought_time
     *
     * @param boughtTime the value for order_info.bought_time
     *
     * @mbg.generated
     */
    public void setBoughtTime(Long boughtTime) {
        this.boughtTime = boughtTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.product_price
     *
     * @return the value of order_info.product_price
     *
     * @mbg.generated
     */
    public Integer getProductPrice() {
        return productPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.product_price
     *
     * @param productPrice the value for order_info.product_price
     *
     * @mbg.generated
     */
    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.buy_quantity
     *
     * @return the value of order_info.buy_quantity
     *
     * @mbg.generated
     */
    public Integer getBuyQuantity() {
        return buyQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.buy_quantity
     *
     * @param buyQuantity the value for order_info.buy_quantity
     *
     * @mbg.generated
     */
    public void setBuyQuantity(Integer buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_assessment
     *
     * @return the value of order_info.order_assessment
     *
     * @mbg.generated
     */
    public Integer getOrderAssessment() {
        return orderAssessment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_assessment
     *
     * @param orderAssessment the value for order_info.order_assessment
     *
     * @mbg.generated
     */
    public void setOrderAssessment(Integer orderAssessment) {
        this.orderAssessment = orderAssessment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.order_state
     *
     * @return the value of order_info.order_state
     *
     * @mbg.generated
     */
    public Integer getOrderState() {
        return orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.order_state
     *
     * @param orderState the value for order_info.order_state
     *
     * @mbg.generated
     */
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.product_img
     *
     * @return the value of order_info.product_img
     *
     * @mbg.generated
     */
    public String getProductImg() {
        return productImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.product_img
     *
     * @param productImg the value for order_info.product_img
     *
     * @mbg.generated
     */
    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.product_id
     *
     * @return the value of order_info.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.product_id
     *
     * @param productId the value for order_info.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.take_delivery_name
     *
     * @return the value of order_info.take_delivery_name
     *
     * @mbg.generated
     */
    public String getTakeDeliveryName() {
        return takeDeliveryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.take_delivery_name
     *
     * @param takeDeliveryName the value for order_info.take_delivery_name
     *
     * @mbg.generated
     */
    public void setTakeDeliveryName(String takeDeliveryName) {
        this.takeDeliveryName = takeDeliveryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.take_delivery_address
     *
     * @return the value of order_info.take_delivery_address
     *
     * @mbg.generated
     */
    public String getTakeDeliveryAddress() {
        return takeDeliveryAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.take_delivery_address
     *
     * @param takeDeliveryAddress the value for order_info.take_delivery_address
     *
     * @mbg.generated
     */
    public void setTakeDeliveryAddress(String takeDeliveryAddress) {
        this.takeDeliveryAddress = takeDeliveryAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.take_delivery_phone
     *
     * @return the value of order_info.take_delivery_phone
     *
     * @mbg.generated
     */
    public Integer getTakeDeliveryPhone() {
        return takeDeliveryPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.take_delivery_phone
     *
     * @param takeDeliveryPhone the value for order_info.take_delivery_phone
     *
     * @mbg.generated
     */
    public void setTakeDeliveryPhone(Integer takeDeliveryPhone) {
        this.takeDeliveryPhone = takeDeliveryPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.user_phone
     *
     * @return the value of order_info.user_phone
     *
     * @mbg.generated
     */
    public Integer getUserPhone() {
        return userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.user_phone
     *
     * @param userPhone the value for order_info.user_phone
     *
     * @mbg.generated
     */
    public void setUserPhone(Integer userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_info.product_color
     *
     * @return the value of order_info.product_color
     *
     * @mbg.generated
     */
    public String getProductColor() {
        return productColor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_info.product_color
     *
     * @param productColor the value for order_info.product_color
     *
     * @mbg.generated
     */
    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }
}