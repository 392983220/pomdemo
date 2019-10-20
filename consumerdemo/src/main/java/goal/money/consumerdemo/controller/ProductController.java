package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.custom.CurrentUser;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.utils.result.ProductReturn;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.PageVo;
import goal.money.consumerdemo.vo.ProductInfoVo;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.providerdemo.dto.CartInfo;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.dto.UserInfo;
import goal.money.providerdemo.service.CartService;
import goal.money.providerdemo.service.OrderInfoService;
import goal.money.providerdemo.service.ProductService;
import goal.money.providerdemo.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/19 9:43
 */
@Api(tags = "1.1")
@RestController
@RequestMapping(value = "/show")
public class ProductController {
    @Reference
    private ProductService productService;
    @Reference
    private UserInfoService userInfoService;
    @Reference
    private OrderInfoService orderInfoService;
    @Reference
    private CartService cartService;


    @ApiOperation(value = "首页商品展示")
    @GetMapping(value = "showIndex")
    public ReturnResult showIndex(String productCategory, PageVo pageVo){
        return ReturnResultUtil.returnSuccessData(1,"首页商品展示",productService.showIndexProductList(productCategory,pageVo.getStartPage(),pageVo.getPageSize()));
    }

    @ApiOperation(value = "根据商品名称模糊查询展示商品")
    @GetMapping(value = "showProductsByName")
    public ReturnResult showProductsByName(String productName,String productCategory ){
        return ReturnResultUtil.returnSuccessData(1,"根据商品名称模糊查询展示商品",productService.queryProductListByName(productName,productCategory));
    }

    @ApiOperation(value = "搜索框内默认商品")
    @GetMapping(value = "defaultProduct")
    public ReturnResult defaultProduct(){
            return ReturnResultUtil.returnSuccessData(1,"搜索框内默认商品",productService.defaultProduct());
    }

    @ApiOperation(value = "登陆状态商品选购")
    @GetMapping(value = "loginChooseGoods")
    @LoginRequired
    public ReturnResult<List<CartInfo>> loginChooseGoods(@CurrentUser UserVo userVo, long productId, int buyQuantity, String color) {
        CartInfo cartInfo = new CartInfo();
        ProductInfo productInfo = productService.selectByPrimaryKey(productId);
        cartInfo.setProductPrice(productInfo.getProductPrice());
        cartInfo.setBuyQuantity(buyQuantity);
        cartInfo.setProductName(productInfo.getProductName());
        cartInfo.setProductId(productId);
        cartInfo.setUserId(userVo.getUserId());
        cartInfo.setProductColor(color);
        cartInfo.setPriceMultiplyQuantity(buyQuantity * productInfo.getProductPrice());
        cartInfo.setProductDescribe(productInfo.getProductDescribe());
        cartInfo.setPhone(userVo.getPhone());
        cartService.insertSelective(cartInfo);
        return ReturnResultUtil.returnSuccessData(1,"登陆状态商品选购",cartService.selectCartList(userVo.getUserId()));
    }

    @ApiOperation(value = "未登录商品选购")
    @GetMapping(value = "unLoginChooseGoods")
    public ReturnResult<Map<String,CartInfo>> unLoginChooseGoods(HttpServletRequest request, long productId, int buyQuantity, String color) {
        CartInfo cartInfo = new CartInfo();
        ProductInfo productInfo = productService.selectByPrimaryKey(productId);
        cartInfo.setProductPrice(productInfo.getProductPrice());
        cartInfo.setBuyQuantity(buyQuantity);
        cartInfo.setProductName(productInfo.getProductName());
        cartInfo.setProductId(productId);
        cartInfo.setProductColor(color);
        cartInfo.setPriceMultiplyQuantity(buyQuantity * productInfo.getProductPrice());
        cartInfo.setProductDescribe(productInfo.getProductDescribe());
        HttpSession session = request.getSession();
        Map<String, CartInfo> map = new HashMap<String, CartInfo>();
        map.put(String.valueOf(productId),cartInfo);
        session.setAttribute("carts",map);
        return ReturnResultUtil.returnSuccessData(1,"未登录商品选购",map);
    }

    @ApiOperation(value = "未登录状态下商品详情")
    @GetMapping(value = "unLoginProductDetail")
    public ReturnResult<ProductInfo> unLoginProductDetail(String  productName,String productColor){
        ProductInfo productInfo=productService.queryProductByNameAndColor(productName,productColor);
/*productInfo.setGoodRate( (1.0*productInfo.getProductScore())/(productInfo.getSaleQuantity()*10));*/
        return ReturnResultUtil.returnSuccessData(1,"未登录状态下商品详情",productInfo);
    }

    @ApiOperation(value = "登陆状态下商品详情")
    @GetMapping(value = "loginProductDetail")
    @LoginRequired
    public ReturnResult<ProductReturn> loginProductDetail(@CurrentUser UserVo userVo,String  productName,String productColor){
        ProductInfo productInfo=productService.queryProductByNameAndColor(productName,productColor);
        int point=userInfoService.queryUserPoint(userVo.getPhone());
        ProductReturn productReturn = new ProductReturn();
        BeanUtils.copyProperties(productInfo,productReturn);
        productReturn.setUserAccumulatePoint(point);
        return ReturnResultUtil.returnSuccessData(1,"登陆状态下商品详情",productReturn);
    }

}

