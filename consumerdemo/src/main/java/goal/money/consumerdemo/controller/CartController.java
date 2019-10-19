package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import goal.money.consumerdemo.custom.CurrentUser;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.custom.Space;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.providerdemo.dto.CartInfo;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.service.CartService;
import goal.money.providerdemo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/17 11:44
 */
@Api(tags = "1.0")
@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Reference
    private CartService cartService;
    @Autowired
    private RedisUtils redisUtils;
    @Reference
    private ProductService productService;

    @ApiOperation(value = "展示未登录状态购物车")
    @GetMapping(value = "/showUnLoginCart")
    public Map<String, CartInfo> showUnLoginCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Map<String, CartInfo> cartInfos = (Map<String, CartInfo>) session.getAttribute("carts");
        for (Map.Entry<String, CartInfo> entry : cartInfos.entrySet()) {
            cartService.updatePriceMultiplyQuantity(entry.getValue().getCartId());
        }
        return cartInfos;
    }

    @ApiOperation(value = "展示登录状态购物车")
    @GetMapping(value = "/showLoginCart")
    @LoginRequired
    public List<CartInfo> showLoginCart(@CurrentUser UserVo userVo) {
        List<CartInfo> cartInfos = cartService.selectCartList(userVo.getUserId());
        for (CartInfo cartInfo : cartInfos) {
            cartService.updatePriceMultiplyQuantity(cartInfo.getCartId());
        }
        return cartInfos;
    }

    @ApiOperation(value = "修改登录状态的下的购物车商品数量")
    @GetMapping(value = "updateLoginBuyQuantity")
    @LoginRequired
    public String updateLoginBuyQuantity(@CurrentUser UserVo userVo, Long productId, int buyQuantity) {
        CartInfo cartInfo = cartService.queryCart(userVo.getUserId(), productId);
        int productQuantity = productService.queryQuantity(cartInfo.getProductId());
        if (productQuantity >= buyQuantity) {
            cartService.updateBuyQuantity(buyQuantity);
            if (buyQuantity > 0) {
                cartService.updatePriceMultiplyQuantity(cartInfo.getCartId());
                CartInfo updatedCart = cartService.queryCartById(cartInfo.getCartId());
                return null;
            } else {
                cartService.deleteByPrimaryKey(cartInfo.getCartId());
                return null;
            }
        } else return "库存不足";
    }

    @ApiOperation(value = "修改未登录状态的下的购物车商品数量")
    @GetMapping(value = "updateUnLoginBuyQuantity")
    public String updateUnLoginBuyQuantity(HttpServletRequest request, long cartId, int buyQuantity) {
        HttpSession session = request.getSession();
        Map<String, CartInfo> cartInfos = (Map<String, CartInfo>) session.getAttribute("carts");
        CartInfo cartInfo = new CartInfo();
        for (Map.Entry<String, CartInfo> entry : cartInfos.entrySet()) {
            if (cartId == entry.getValue().getCartId())
                BeanUtils.copyProperties(entry, cartInfo);
        }
        int productQuantity = productService.queryQuantity(cartInfo.getProductId());
        if (productQuantity >= buyQuantity) {
            cartService.updateBuyQuantity(buyQuantity);
            if (buyQuantity > 0) {
                cartService.updatePriceMultiplyQuantity(cartInfo.getCartId());
                CartInfo updatedCart = cartService.queryCartById(cartInfo.getCartId());
                return null;
            } else {
                cartService.deleteByPrimaryKey(cartInfo.getCartId());
                return null;
            }
        } else return "库存不足";
    }

    @ApiOperation(value = "登录用户删除购物车")
    @GetMapping(value = "/deleteLoginUserCart")
    @LoginRequired
    public String deleteLoginUserCart(Long cartId, @CurrentUser UserVo userVo) {
        if (1 == cartService.deleteByPrimaryKey(cartId)) {
            return "删除成功";
        }
        return "删除失败";
    }

    @ApiOperation(value = "登录用户删除购物车")
    @GetMapping(value = "/deleteUnLoginUserCart")
    public Map<String, CartInfo> deleteUnLoginUserCart(HttpServletRequest request, long cartId) {
        HttpSession session = request.getSession();
        Map<String, CartInfo> cartInfos = (Map<String, CartInfo>) session.getAttribute("carts");
        Iterator<String> iter = cartInfos.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (Integer.valueOf(key) == cartId) {
                iter.remove();
            }
        }return cartInfos;
    }


}
