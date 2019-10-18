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

    @Override
    public OrderInfo selectByPrimaryKey(Long orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int deleteByPrimaryKey(Long orderId) {
        return orderInfoMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public List<OrderInfo> queryByNo(String orderNumber,int orderState) {
        return orderInfoMapper.queryByNo(orderNumber,orderState);
    }

    @Override
    public List<OrderInfo> queryByName(String productName,int orderState) {
        return orderInfoMapper.queryByName(productName,orderState);
    }

    @Override
    public OrderInfo queryByOrderNo(String orderNumber) {
        return orderInfoMapper.queryByOrderNo(orderNumber);
    }

    @Override
    public void updateOrderAssessment(OrderInfo orderInfo) {
        orderInfoMapper.updateOrderAssessment(orderInfo);
    }

}
