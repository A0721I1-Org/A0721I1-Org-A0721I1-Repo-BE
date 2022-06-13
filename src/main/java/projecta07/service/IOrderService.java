package projecta07.service;

import projecta07.model.Order;

import java.util.List;


public interface IOrderService {
    Order findOrderOfTableById(Long id);
    void cancelTable(Long id);
    List<Order> findAll();
    Order save(Order order);
   // tạo phương thức tạo order truyền id bàn xuống cho tuấn khi click đặt bàn .
}
