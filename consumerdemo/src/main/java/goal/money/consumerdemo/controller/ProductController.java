//package goal.money.consumerdemo.controller;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import goal.money.consumerdemo.custom.CurrentUser;
//import goal.money.consumerdemo.custom.LoginRequired;
//import goal.money.consumerdemo.utils.GetIpAddressUtil;
//import goal.money.consumerdemo.utils.RedisUtils;
//import goal.money.consumerdemo.utils.result.*;
//import goal.money.consumerdemo.vo.PageVo;
//import goal.money.consumerdemo.vo.UserVo;
//import goal.money.providerdemo.dto.CartInfo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
///**
// * @version 1.0
// * @Author 张深
// * @create 2019/10/19 9:43
// */
//@Api(tags = "商品")
//@RestController
//@RequestMapping(value = "/show")
//public class ProductController {
//    @Reference
//    private ProductService productService;
//    @Reference
//    private UserInfoService userInfoService;
//    @Reference
//    private OrderInfoService orderInfoService;
//    @Reference
//    private CartService cartService;
//    @Autowired
//    private RedisUtils redisUtils;
//
//    @ApiOperation(value = "首页商品展示")
//    @GetMapping(value = "showIndex")
//    public ReturnResult<Pages> showIndex(int productCategory, PageVo pageVo, HttpServletRequest request) {
//        List<ProductInfo> obj = productService.showIndexProductList(productCategory, pageVo.getStartPage(), pageVo.getPageSize());
//        Pages pages = new Pages();
//        pages.setCurrentPage(pageVo.getStartPage());
//        pages.setPagesize(pageVo.getPageSize());
//        pages.setCurrList(obj);
//        pages.setProductInfo(defaultProduct());
//        String ip = GetIpAddressUtil.getIpAddr(request);
//        Integer.valueOf(redisUtils.get(ip).toString());
//        return ReturnResultUtil.returnSuccessData(1, "首页商品展示", pages);
//    }
//
//
///*    @ApiOperation(value = "首页类别展示")
//    @GetMapping(value = "showCategory")
//    public ReturnResult<Pages> returnNameAndUrl(int category) {
//        Pages pages = new Pages();
//        NameAndUrl nameAndUrl = new NameAndUrl();
//        for (Map.Entry<Integer, String> entry : nameAndUrl.getProductCategory().entrySet()) {
//            if (entry.getKey() == category) {
//                pages.setName(entry.getValue());
//                pages.setUrl("localhost:8080/show/showIndex?productCategory=" + category);
//                return ReturnResultUtil.returnSuccessData(1, "商品类别及路径", pages);
//            }
//        }
//        return ReturnResultUtil.returnFail(2, "没有该类别商品");
//    }*/
//
//    @ApiOperation(value = "根据商品名称模糊查询展示商品")
//    @GetMapping(value = "showProductsByName")
//    public ReturnResult showProductsByName(String productName, String productCategory) {
//        return ReturnResultUtil.returnSuccessData(1, "根据商品名称模糊查询展示商品", productService.queryProductListByName(productName, productCategory));
//    }
//
//
//    @ApiOperation(value = "登陆状态商品选购")
//    @GetMapping(value = "loginChooseGoods")
//    @LoginRequired
//    public ReturnResult<List<CartInfo>> loginChooseGoods(@CurrentUser UserVo userVo, long productId, int buyQuantity) {
//        CartInfo cartInfo = cartService.queryCart(userVo.getPhone(), productId);
//        if (cartInfo != null) {
//            cartService.updateBuyQuantity(cartInfo.getBuyQuantity() + buyQuantity, cartInfo.getCartId());
//            cartService.updatePriceMultiplyQuantity(cartInfo.getCartId());
//            return ReturnResultUtil.returnSuccessData(1, "登陆状态商品选购", cartService.selectCartList(userVo.getPhone()));
//        }
//        cartInfo = new CartInfo();
//        ProductInfo productInfo = productService.selectByPrimaryKey(productId);
//        cartInfo.setProductPrice(productInfo.getProductPrice());
//        cartInfo.setBuyQuantity(buyQuantity);
//        cartInfo.setProductName(productInfo.getProductName());
//        cartInfo.setProductId(productId);
//        cartInfo.setUserId(userVo.getUserId());
//        cartInfo.setProductColor(productInfo.getProductColor());
//        cartInfo.setPriceMultiplyQuantity(buyQuantity * productInfo.getProductPrice());
//        cartInfo.setProductDescribe(productInfo.getProductDescribe());
//        cartInfo.setPhone(userVo.getPhone());
//        cartService.insertSelective(cartInfo);
//        return ReturnResultUtil.returnSuccessData(1, "登陆状态商品选购", cartService.selectCartList(userVo.getPhone()));
//    }
//
//   /* @ApiOperation(value = "未登录商品选购")
//    @GetMapping(value = "unLoginChooseGoods")
//    public ReturnResult<Map<String, CartInfo>> unLoginChooseGoods(HttpServletRequest request, long productId, int buyQuantity) {
//        HttpSession session = request.getSession();
//        CartInfo cartInfo = new CartInfo();
//        ProductInfo productInfo = productService.selectByPrimaryKey(productId);
//        Map<String, CartInfo> mapTest = new HashMap<>();
//        Map<String, CartInfo> map = (Map<String, CartInfo>) session.getAttribute("carts");
//        if (map == null) {
//            map = new HashMap<String, CartInfo>();
//        }
//        if (map.get(String.valueOf(productId)) == null) {
//            if (buyQuantity > productInfo.getProductQuantity()) {
//                return ReturnResultUtil.returnFail(2, "库存不足");
//            }
//            cartInfo.setProductPrice(productInfo.getProductPrice());
//            cartInfo.setBuyQuantity(buyQuantity);
//            cartInfo.setProductName(productInfo.getProductName());
//            cartInfo.setProductId(productId);
//            cartInfo.setGoodRate(productInfo.getGoodRate());
//            cartInfo.setProductColor(productInfo.getProductColor());
//            cartInfo.setPriceMultiplyQuantity(buyQuantity * productInfo.getProductPrice());
//            cartInfo.setProductDescribe(productInfo.getProductDescribe());
//            map.put(String.valueOf(productId), cartInfo);
//        } else {
//            CartInfo cartInfo1 = map.get(String.valueOf(productId));
//            if (buyQuantity + cartInfo1.getBuyQuantity() > productInfo.getProductQuantity()) {
//                return ReturnResultUtil.returnFail(2, "库存不足");
//            }
//            cartInfo1.setBuyQuantity(buyQuantity + cartInfo1.getBuyQuantity());
//            cartInfo1.setPriceMultiplyQuantity(cartInfo1.getBuyQuantity() * cartInfo1.getProductPrice());
//            map.put(String.valueOf(productId), cartInfo1);
//        }
//        session.setAttribute("carts", map);
//        return ReturnResultUtil.returnSuccessData(1, "未登录商品选购", map);
//    }*/
//
//    @ApiOperation(value = "未登录状态下商品详情")
//    @GetMapping(value = "unLoginProductDetail")
//    public ReturnResult<ProductInfo> unLoginProductDetail(String productName, String productColor) {
//        ProductInfo productInfo = productService.queryProductByNameAndColor(productName, productColor);
//        /*productInfo.setGoodRate( (1.0*productInfo.getProductScore())/(productInfo.getSaleQuantity()*10));*/
//        return ReturnResultUtil.returnSuccessData(1, "未登录状态下商品详情", productInfo);
//    }
//
//    @ApiOperation(value = "登陆状态下商品详情")
//    @GetMapping(value = "loginProductDetail")
//    @LoginRequired
//    public ReturnResult<ProductReturn> loginProductDetail(@CurrentUser UserVo userVo, String productName, String productColor) {
//        ProductInfo productInfo = productService.queryProductByNameAndColor(productName, productColor);
//        int point = userInfoService.queryUserPoint(userVo.getPhone());
//        ProductReturn productReturn = new ProductReturn();
//        BeanUtils.copyProperties(productInfo, productReturn);
//        productReturn.setUserAccumulatePoint(point);
//        return ReturnResultUtil.returnSuccessData(1, "登陆状态下商品详情", productReturn);
//    }
//
//}
//
