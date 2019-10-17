package goal.money.consumerdemo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import goal.money.consumerdemo.vo.PageVo;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.service.OrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    public List<OrderInfo> queryOrder(PageVo pageVo, int orderState){
        return orderInfoService.queryByState(orderState,pageVo.getStartPage(),pageVo.getPageSize());
    }
}
