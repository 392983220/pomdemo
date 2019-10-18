package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.vo.PageVo;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.dto.Recycle;
import goal.money.providerdemo.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "订单")
@RequestMapping(value = "order")
public class OrderController {
    @Reference
    private OrderInfoService orderInfoService;

    @GetMapping(value = "queryOrder")
    @ApiOperation(value = "查询订单")
    public List<OrderInfo> queryOrder(PageVo pageVo, int orderState) {
        return orderInfoService.queryByState(orderState, pageVo.getStartPage(), pageVo.getPageSize());
    }

    @GetMapping(value = "deleteOrder")
    @ApiOperation(value = "删除订单")
    public void deleteOrder(Long orderId) {
        OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
        Recycle recycle = new Recycle();
        BeanUtils.copyProperties(orderInfo, recycle);
        if (null != recycle.getOrderId()) {
            orderInfoService.deleteByPrimaryKey(orderId);
        }
    }

    @GetMapping(value = "queryOrderByNumber")
    @ApiOperation("根据订单号模糊查询订单")
    public List<OrderInfo> queryOrderByNumber(String orderNumber, int orderState) {
        return orderInfoService.queryByNo(orderNumber, orderState);
    }

    @GetMapping(value = "queryOrderByName")
    @ApiOperation("根据订单号模糊查询订单")
    public List<OrderInfo> queryOrderByName(String orderName, int orderState) {
        return orderInfoService.queryByName(orderName, orderState);
    }

    @GetMapping(value = "givePoint")
    @ApiOperation(value = "用户打分")
    public String givePoint(OrderInfo orderInfo, int point) {
        if (orderInfo.getOrderState() == 4) {
            if (point != 9) {
                orderInfo.setOrderAssessment(point);
                orderInfoService.updateOrderAssessment(orderInfo);
                //更改商品表中商品的总评分
                return "感谢评价";
            } else {
                return null;
            }
        } else {
            return "暂时不能打分";
        }
    }
}
