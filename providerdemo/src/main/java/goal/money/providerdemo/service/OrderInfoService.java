package goal.money.providerdemo.service;


import goal.money.providerdemo.dto.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    //展示订单
    List<OrderInfo> queryOrderByPhone(String phone, int orderStatus, int startPage, int pageSize);

    int insertSelective(OrderInfo record);




}
