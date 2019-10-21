package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import goal.money.consumerdemo.custom.CurrentUser;
import goal.money.consumerdemo.custom.LoginRequired;
import goal.money.consumerdemo.utils.RedisUtils;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
import goal.money.consumerdemo.vo.PageVo;
import goal.money.consumerdemo.vo.UserVo;
import goal.money.providerdemo.dto.DetailInfo;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.dto.Recycle;
import goal.money.providerdemo.service.OrderInfoService;
import goal.money.providerdemo.service.RecycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Api(tags = "订单")
@RequestMapping(value = "order")
public class OrderController {
    @Reference
    private OrderInfoService orderInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @Reference
    private RecycleService recycleService;

    @GetMapping(value = "insertIntoOrder")
    @ApiOperation(value = "生成订单")
    @LoginRequired
    public ReturnResult insertIntoOrder(@CurrentUser UserVo userVo, DetailInfo detailInfo,
                                        String takeDeliveryName, String takeDeliveryAddress, String takeDeliveryPhone) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPhone(userVo.getPhone());
        orderInfo.setTakeDeliveryName(takeDeliveryName);
        orderInfo.setTakeDeliveryAddress(takeDeliveryAddress);
        orderInfo.setTakeDeliveryPhone(takeDeliveryPhone);
        orderInfo.setBuyQuantity(orderInfoService.querySumProductQuantity(userVo.getPhone()));
        orderInfo.setSumPrice(orderInfoService.querySumPrice(userVo.getPhone()));
        orderInfo.setOrderNumber(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20));
        if (orderInfo.getPhone().equals(userVo.getPhone()) &&
                orderInfo.getTakeDeliveryAddress() != null && orderInfo.getTakeDeliveryName() != null && orderInfo.getTakeDeliveryPhone() != null){
            orderInfoService.insertIntoOrder(orderInfo);
            redisUtils.set(userVo.getPhone(), JSONObject.toJSONString(orderInfo), 60);
            return ReturnResultUtil.returnSuccessData(1, "生成订单", orderInfo);
        }else return ReturnResultUtil.returnFail(2,"订单信息不完整");
            
    }

    @GetMapping(value = "queryOrder")
    @ApiOperation(value = "查询订单")
    @LoginRequired
    public ReturnResult<List<OrderInfo>> queryOrder(@CurrentUser UserVo userVo, PageVo pageVo, int orderState) {
        List<OrderInfo> orderInfos = orderInfoService.queryOrderByState(userVo.getPhone(), orderState, pageVo.getStartPage(), pageVo.getPageSize());
        return ReturnResultUtil.returnSuccessData(1, "查询订单", orderInfos);
    }

    @GetMapping(value = "deleteOrder")
    @ApiOperation(value = "删除订单")
    public ReturnResult<OrderInfo> deleteOrder(Long orderId) {
        OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
        Recycle recycle = new Recycle();
        BeanUtils.copyProperties(orderInfo, recycle);
        if (null != recycle.getOrderId()) {
            recycleService.insertSelective(recycle);
            orderInfoService.deleteByPrimaryKey(orderId);
            return ReturnResultUtil.returnSuccessData(1, "删除订单成功", orderInfo);
        }
        return ReturnResultUtil.returnFail(2, "删除订单失败");
    }

    @GetMapping(value = "queryOrderByNumber")
    @ApiOperation("根据订单号模糊查询订单")
    @LoginRequired
    public ReturnResult<List<OrderInfo>> queryOrderByNumber(@CurrentUser UserVo userVo, String orderNumber, int orderState) {
        return ReturnResultUtil.returnSuccessData(1, "根据订单号模糊查询订单",
                orderInfoService.queryOrderByNo(userVo.getPhone(), orderNumber, orderState));
    }

    @GetMapping(value = "queryOrderByName")
    @ApiOperation("根据订单号模糊查询订单")
    @LoginRequired
    public ReturnResult<List<OrderInfo>> queryOrderByName(@CurrentUser UserVo userVo, String orderName, int orderState) {
        return ReturnResultUtil.returnSuccessData(1, "根据订单号模糊查询订单",
                orderInfoService.queryOrderByName(userVo.getPhone(), orderName, orderState));
    }

    /*@GetMapping(value = "givePoint")
    @ApiOperation(value = "用户打分")
    @LoginRequired
    public ReturnResult<OrderInfo> givePoint(@CurrentUser UserVo userVo, DetailInfo detailInfo, int point) {


        if (orderInfo.getOrderState() == 4) {
            orderInfo.setOrderAssessment(point);
            orderInfoService.updateOrderAssessment(orderInfo);
            return ReturnResultUtil.returnSuccessData(1, "感谢评分", orderInfoService.selectByPrimaryKey(orderInfo.getOrderId()));
        } else {
            return ReturnResultUtil.returnFail(2, "订单暂时不能评价");
        }
    }*/
}
