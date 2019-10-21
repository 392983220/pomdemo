package goal.money.providerdemo.mapper;

import goal.money.providerdemo.dto.CartInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long cartId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_info
     *
     * @mbg.generated
     */
    int insert(CartInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_info
     *
     * @mbg.generated
     */
    int insertSelective(CartInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_info
     *
     * @mbg.generated
     */
    CartInfo selectByPrimaryKey(Long cartId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(CartInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cart_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(CartInfo record);

    List<CartInfo> selectCartList(Long userId);

    void updatePriceMultiplyQuantity(Long cartId);

    CartInfo queryCart(@Param("phone") String phone, @Param("productId") Long productId);

    void updateBuyQuantity(@Param("buyQuantity")int buyQuantity,@Param("cartId")long cartId);

    CartInfo queryCartById(Long cartId);
}