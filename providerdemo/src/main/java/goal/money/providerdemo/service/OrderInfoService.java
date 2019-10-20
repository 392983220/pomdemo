package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    void updateOrderState(String orderNumber);

    List<OrderInfo> queryByState(int orderState,int startPage,int pageSize);

    OrderInfo selectByPrimaryKey(Long orderId);

    int deleteByPrimaryKey(Long orderId);

    List<OrderInfo> queryByNo(String orderNumber,int orderState);

    List<OrderInfo> queryByName(String productName,int orderState);

    OrderInfo queryByOrderNo(String orderNumber);

    void updateOrderAssessment(OrderInfo orderInfo);




}
