package goal.money.providerdemo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import goal.money.providerdemo.mapper.OrderInfoMapper;
import goal.money.providerdemo.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public void updateOrderState(String orderNumber) {
        orderInfoMapper.updateOrderState(orderNumber);
    }
}
