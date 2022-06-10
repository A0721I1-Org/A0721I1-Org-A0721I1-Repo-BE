package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.repository.IOrderRepository;
import projecta07.service.IOrderService;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order findOrderOfTableById(Long id) {
        return orderRepository.getAllOrderByIdTable(id);
    }

    @Override
    public void cancelTable(Long id) {
        orderRepository.removeOrderByTable_IdTable(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }


}
