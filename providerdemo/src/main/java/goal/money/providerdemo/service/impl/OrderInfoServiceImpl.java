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
    public List<OrderInfo> queryOrderByPhone(String phone, int orderStatus, int startPage, int pageSize) {
        return orderInfoMapper.queryOrderByPhone(phone, orderStatus, startPage, pageSize);
    }

    @Override
    public int insertSelective(OrderInfo record) {
        return orderInfoMapper.insertSelective(record);
    }

}
