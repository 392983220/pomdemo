package goal.money.providerdemo.service;


import goal.money.providerdemo.dto.OrderInfo;

import java.util.List;

public interface OrderInfoService {

    void insertIntoOrder(OrderInfo orderInfo);

    int querySumProductQuantity(String phone);

    int querySumPrice(String phone);

    List<OrderInfo> queryOrderByState(String phone, int orderState,int startPage,int pageSize);

    OrderInfo selectByPrimaryKey(Long orderId);

    int deleteByPrimaryKey(Long orderId);

    List<OrderInfo> queryOrderByNo(String phone, String orderNumber,int orderState);

    List<OrderInfo> queryOrderByName(String phone, String productName,int orderState);


/*    void updateOrderState(String orderNumber);

    List<OrderInfo> queryByState(int orderState,int startPage,int pageSize);

    OrderInfo selectByPrimaryKey(Long orderId);

    int deleteByPrimaryKey(Long orderId);

    List<OrderInfo> queryByNo(String orderNumber,int orderState);

    List<OrderInfo> queryByName(String productName,int orderState);

    OrderInfo queryByOrderNo(String orderNumber);

    void updateOrderAssessment(OrderInfo orderInfo);*/




}
