package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.utils.result.ReturnResult;
import goal.money.consumerdemo.utils.result.ReturnResultUtil;
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
    public ReturnResult<List<OrderInfo>> queryOrder(PageVo pageVo, int orderState) {
        List<OrderInfo> orderInfos= orderInfoService.queryByState(orderState, pageVo.getStartPage(), pageVo.getPageSize());
        return ReturnResultUtil.returnSuccessData(1,"查询订单",orderInfos);
    }

    @GetMapping(value = "deleteOrder")
    @ApiOperation(value = "删除订单")
    public ReturnResult<OrderInfo> deleteOrder(Long orderId) {
        OrderInfo orderInfo = orderInfoService.selectByPrimaryKey(orderId);
        Recycle recycle = new Recycle();
        BeanUtils.copyProperties(orderInfo, recycle);
        if (null != recycle.getOrderId()) {
            orderInfoService.deleteByPrimaryKey(orderId);
            return ReturnResultUtil.returnSuccessData(1,"删除订单成功",orderInfo);
        }return ReturnResultUtil.returnFail(2,"删除订单失败");
    }

    @GetMapping(value = "queryOrderByNumber")
    @ApiOperation("根据订单号模糊查询订单")
    public ReturnResult<List<OrderInfo>> queryOrderByNumber(String orderNumber, int orderState) {
        return ReturnResultUtil.returnSuccessData(1,"根据订单号模糊查询订单",orderInfoService.queryByNo(orderNumber, orderState));
    }

    @GetMapping(value = "queryOrderByName")
    @ApiOperation("根据订单号模糊查询订单")
    public ReturnResult<List<OrderInfo>> queryOrderByName(String orderName, int orderState) {
        return ReturnResultUtil.returnSuccessData(1,"根据订单号模糊查询订单",orderInfoService.queryByName(orderName, orderState));
    }

    @GetMapping(value = "givePoint")
    @ApiOperation(value = "用户打分")
    public ReturnResult<OrderInfo> givePoint(OrderInfo orderInfo, int point) {
        if (orderInfo.getOrderState() == 4) {
                orderInfo.setOrderAssessment(point);
                orderInfoService.updateOrderAssessment(orderInfo);
                return ReturnResultUtil.returnSuccessData(1,"感谢评分",orderInfoService.selectByPrimaryKey(orderInfo.getOrderId()));
        } else {
            return ReturnResultUtil.returnFail(2,"订单暂时不能评价");
        }
    }
}
