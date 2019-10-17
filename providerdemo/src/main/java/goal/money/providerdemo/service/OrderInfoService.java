package goal.money.providerdemo.service;

import goal.money.providerdemo.dto.OrderInfo;

import java.util.List;

public interface OrderInfoService {
    void updateOrderState(String orderNumber);

    List<OrderInfo> queryByState(int orderState,int startPage,int pageSize);

}
