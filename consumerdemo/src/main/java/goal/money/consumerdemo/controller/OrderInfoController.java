package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.PageVo;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.dto.ProductInfo;
import goal.money.providerdemo.service.OrderInfoService;
import goal.money.providerdemo.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0
 * @Author 张深
 * @create 2019/10/22 18:57
 */
@Api(tags = "订单")
@RestController
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {
    @Reference
    private OrderInfoService orderInfoService;
    @Reference
    private ProductInfoService productInfoService;

    @ApiOperation("生成订单")
    @PostMapping(value = "/createOrder")
    @LoginRequired
    public ReturnResult createOrder(String productIds) {
        HashMap<String, String> hashmap = JSONObject.parseObject(productIds, HashMap.class);
        for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
            long productId=Long.parseLong(entry.getValue());
            ProductInfo productInfo=productInfoService.selectByPrimaryKey(productId);
            OrderInfo orderInfo=new OrderInfo();
            BeanUtils.copyProperties(productInfo,orderInfo);
            orderInfoService.insertSelective(orderInfo);
        }
        return null;
    }

    @ApiOperation(value = "展示订单")
    @GetMapping(value = "/getOrders")
    public ReturnResult getOrders(PageVo pageVo, int orderStatus, String phone) {
        List<OrderInfo> orderInfos = orderInfoService.queryOrderByPhone(phone, orderStatus, pageVo.getStartPage(), pageVo.getPageSize());
        return ReturnResultUtil.returnSuccessData(1, "success", orderInfos);
    }
}
