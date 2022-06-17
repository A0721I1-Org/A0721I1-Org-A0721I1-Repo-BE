package projecta07.service;

import projecta07.model.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> getOrderDetailByOrderId(Long id);

    void deleteOrderDetailInTable(Long id);
}