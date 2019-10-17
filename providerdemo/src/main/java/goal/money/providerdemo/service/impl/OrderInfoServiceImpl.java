package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.dto.OrderInfo;
import goal.money.providerdemo.mapper.OrderInfoMapper;
import goal.money.providerdemo.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public void updateOrderState(String orderNumber) {
        orderInfoMapper.updateOrderState(orderNumber);
    }

    @Override
    public List<OrderInfo> queryByState(int orderState,int startPage,int pageSize) {
        return orderInfoMapper.queryByState(orderState,startPage,pageSize);
    }
}
