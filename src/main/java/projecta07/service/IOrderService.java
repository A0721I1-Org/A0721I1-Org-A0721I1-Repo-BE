package projecta07.service;

import projecta07.model.Order;

import java.util.List;


public interface IOrderService {
    Order findOrderOfTableById(Long id);

    void cancelTable(Long id);

    List<Order> findAll();

    Order saveOrder(Order order);


}
