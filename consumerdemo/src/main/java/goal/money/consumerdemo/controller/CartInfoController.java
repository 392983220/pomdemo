package goal.money.consumerdemo.controller;

import com.alibaba.fastjson.JSONObject;
import goal.money.consumerdemo.custom.CurrentUser;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.utils.GetIpAddressUtil;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.providerdemo.dto.CartInfo;
import goal.money.providerdemo.service.CartInfoService;
import goal.money.providerdemo.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @authorwxf
 * @date10/22
 */
@Api(tags = "购物车")
@RestController
@RequestMapping(value = "/cartInfo")
public class CartInfoController {

    @Autowired
    private RedisUtils redisUtils;

    @Reference
    private CartInfoService cartInfoService;

    @Reference
    private ProductInfoService productInfoService;

    @ApiOperation(value = "加入购物车")
    @GetMapping(value = "/insertCart")
    @LoginRequired
    public ReturnResult insertCart(@CurrentUser UserVo userVo, HttpServletRequest request,
                                   Long productId) {
        if (userVo == null) {
            String key = GetIpAddressUtil.getIpAddr(request);
            Map<String, Integer> map = null;
            if (null != redisUtils.get(key)) {
                map = JSONObject.parseObject(redisUtils.get(key).toString(), HashMap.class);
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (entry.getKey().equals(String.valueOf(productId))) {
                        entry.setValue(entry.getValue() + 1);
                        redisUtils.set(key, JSONObject.toJSONString(map));
                        String size = "购物车数量" + map.size();
                        List list = new ArrayList();
                        list.add(size);
                        list.add(map);
                        return ReturnResultUtil.returnSuccessData(1, "购物车商品数量加1", map);
                    }
                }
                map.put(productId.toString(), 1);
                redisUtils.set(key, JSONObject.toJSONString(map));
                String size = "购物车数量" + map.size();
                List list = new ArrayList();
                list.add(size);
                list.add(map);
                return ReturnResultUtil.returnSuccessData(1, "添加购物车成功", map);
            }
            map = new HashMap<String, Integer>();
            map.put(productId.toString(), 1);
            redisUtils.set(key, JSONObject.toJSONString(map));
            String size = "购物车数量" + map.size();
            List list = new ArrayList();
            list.add(size);
            list.add(map);
            return ReturnResultUtil.returnSuccessData(1, "添加购物车成功", map);
        } else {
            CartInfo cartInfo = new CartInfo();
            cartInfo.setProductId(productId);
            cartInfo.setBuyQuantity(1);
            cartInfo.setPhone(userVo.getPhone());
            cartInfo.setPrice(productInfoService.selectByProductInfoId(productId).getProductPrice());
            cartInfoService.insertSelective(cartInfo);
            int size = cartInfoService.queryCartByPhone(userVo.getPhone()).size();
            return ReturnResultUtil.returnSuccessData(1, "添加购物车成功", size);
        }


    }
}