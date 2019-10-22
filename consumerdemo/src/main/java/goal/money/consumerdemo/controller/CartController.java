//package goal.money.consumerdemo.controller;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import goal.money.consumerdemo.custom.CurrentUser;
//import goal.money.consumerdemo.custom.LoginRequired;
//import goal.money.consumerdemo.utils.RedisUtils;
//import goal.money.consumerdemo.utils.result.ReturnResult;
//import goal.money.consumerdemo.utils.result.ReturnResultUtil;
//import goal.money.consumerdemo.vo.UserVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
///**
// * @version 1.0
// * @Author 张深
// * @create 2019/10/17 11:44
// */
//@Api(tags = "购物车")
//@RestController
//@RequestMapping(value = "/cart")
//public class CartController {
//    @Reference
//    private CartService cartService;
//    @Autowired
//    private RedisUtils redisUtils;
//    @Reference
//    private ProductService productService;
//
//    @ApiOperation("加入未登录状态购物车")
//    @GetMapping(value = "insertUnLoginCart")
//    public ReturnResult insertUnLoginCart(HttpServletRequest request,long prodcutId,int buyQuantity){
//        HttpSession session = request.getSession();
//        Map<String, CartInfo> cartInfos = (Map<String, CartInfo>) session.getAttribute(session.getId());
//        boolean flag=false;
//        if (cartInfos==null){
//            cartInfos=new HashMap<String, CartInfo>();
//        }
//        for (Map.Entry<String, CartInfo> entry : cartInfos.entrySet()){
//            if (entry.getKey().equals(String.valueOf(prodcutId))){
//                flag=true;
//              return   ReturnResultUtil.returnSuccessData(1,"购物车里面已经有啦",cartInfos);
//            }
//        }if (!flag){
//            CartInfo cartInfo=new CartInfo();
//            ProductInfo productInfo=productService.selectByPrimaryKey(prodcutId);
//            cartInfo.setBuyQuantity(buyQuantity);
//            cartInfo.setGoodRate(productInfo.getGoodRate());
//            cartInfo.setProductColor(productInfo.getProductColor());
//            cartInfo.setProductId(prodcutId);
//            cartInfo.setProductPrice(productInfo.getProductPrice());
//            cartInfo.setPriceMultiplyQuantity(productInfo.getProductPrice()*buyQuantity);
//            cartInfo.setProductName(productInfo.getProductName());
//            cartInfo.setProductDescribe(productInfo.getProductDescribe());
//            cartInfos.put(String.valueOf(prodcutId),cartInfo);
//        }
//        session.setAttribute(session.getId(),productService.selectByPrimaryKey(prodcutId));
//        return ReturnResultUtil.returnSuccessData(1,"加入未登录状态购物车",session.getAttribute(session.getId()));
//    }
//
//    @ApiOperation(value = "展示未登录状态购物车")
//    @GetMapping(value = "/showUnLoginCart")
//    public ReturnResult<Map<String, CartInfo>> showUnLoginCart(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Map<String, CartInfo> cartInfos = (Map<String, CartInfo>) session.getAttribute(session.getId());
//        if (cartInfos == null) {
//            return ReturnResultUtil.returnSuccess(1, "购物车空空如也");
//        }
//        return ReturnResultUtil.returnSuccessData(1, "success", session.getAttribute(session.getId()));
//    }
//
//    @ApiOperation(value = "展示登录状态购物车")
//    @GetMapping(value = "/showLoginCart")
//    @LoginRequired
//    public ReturnResult<Map<String, CartInfo>> showLoginCart(@CurrentUser UserVo userVo) {
//        List<CartInfo> cartInfos = cartService.selectCartList(userVo.getPhone());
//        for (CartInfo cartInfo : cartInfos) {
//            cartService.updatePriceMultiplyQuantity(cartInfo.getCartId());
//        }
//        return ReturnResultUtil.returnSuccessData(1, "success", cartInfos);
//    }
//
//    @ApiOperation(value = "修改登录状态的下的购物车商品数量")
//    @GetMapping(value = "updateLoginBuyQuantity")
//    @LoginRequired
//    public ReturnResult<CartInfo> updateLoginBuyQuantity(@CurrentUser UserVo userVo, Long productId, int buyQuantity) {
//        CartInfo cartInfo = cartService.queryCart(userVo.getPhone(), productId);
//        int productQuantity = productService.queryQuantity(cartInfo.getProductId());
//        if (productQuantity >= buyQuantity) {
//            cartService.updateBuyQuantity(buyQuantity, cartInfo.getCartId());
//            if (buyQuantity > 0) {
//                cartService.updatePriceMultiplyQuantity(cartInfo.getCartId());
//                CartInfo updatedCart = cartService.queryCartById(cartInfo.getCartId());
//                return ReturnResultUtil.returnSuccessData(1, "success", updatedCart);
//            } else {
//                cartService.deleteByPrimaryKey(cartInfo.getCartId());
//                return ReturnResultUtil.returnSuccess(1, "商品已从购物车中删除");
//            }
//        } else return ReturnResultUtil.returnFail(2, "库存不足");
//    }
//
//    @ApiOperation(value = "修改未登录状态的下的购物车商品数量")
//    @GetMapping(value = "updateUnLoginBuyQuantity")
//    public ReturnResult<CartInfo> updateUnLoginBuyQuantity(HttpServletRequest request, long productId, int buyQuantity) {
//        HttpSession session = request.getSession();
//        Map<String, CartInfo> cartInfos = (Map<String, CartInfo>) session.getAttribute("carts");
//        int productQuantity = 0;
//        for (Map.Entry<String, CartInfo> entry : cartInfos.entrySet()) {
//            if (productId == entry.getValue().getProductId())
//                productQuantity = productService.queryQuantity(entry.getValue().getProductId());
//            if (buyQuantity + entry.getValue().getBuyQuantity() <= 0) {
//                Iterator<String> iter = cartInfos.keySet().iterator();
//                while (iter.hasNext()) {
//                    String key = iter.next();
//                    if (Integer.valueOf(key) == productId) {
//                        iter.remove();
//                    }
//                }
//                return ReturnResultUtil.returnSuccessData(1, "删除成功", session.getAttribute(session.getId()));
//            }
//            if (productQuantity >= buyQuantity) {
//                entry.getValue().setBuyQuantity(buyQuantity);
//            } else {
//                return ReturnResultUtil.returnFail(2, "库存不足");
//            }
//            session.setAttribute(session.getId(), cartInfos);
//            return ReturnResultUtil.returnSuccessData(1, "修改成功", session.getAttribute(session.getId()));
//        }
//        return ReturnResultUtil.returnFail(1, "购物车内没有该商品");
//    }
//
//    @ApiOperation(value = "登录用户删除购物车")
//    @GetMapping(value = "/deleteLoginUserCart")
//    @LoginRequired
//    public ReturnResult deleteLoginUserCart(Long cartId, @CurrentUser UserVo userVo) {
//        if (1 == cartService.deleteByPrimaryKey(cartId)) {
//            return ReturnResultUtil.returnSuccess(1, "删除成功");
//        }
//        return ReturnResultUtil.returnFail(2, "删除失败");
//    }
//
//    @ApiOperation(value = "未登录用户删除购物车")
//    @GetMapping(value = "/deleteUnLoginUserCart")
//    public ReturnResult<Map<String, CartInfo>> deleteUnLoginUserCart(HttpServletRequest request, long cartId) {
//        HttpSession session = request.getSession();
//        Map<String, CartInfo> cartInfos = (Map<String, CartInfo>) session.getAttribute(session.getId());
//        Iterator<String> iter = cartInfos.keySet().iterator();
//        while (iter.hasNext()) {
//            String key = iter.next();
//            if (Integer.valueOf(key) == cartId) {
//                iter.remove();
//            }
//        }
//        return ReturnResultUtil.returnSuccessData(1, "删除成功", session.getAttribute(session.getId()));
//    }
//}
