package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import projecta07.model.Order;
import projecta07.model.OrderDetail;
import projecta07.repository.IOrderRepository;
import projecta07.service.IOrderService;

import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order findOrderOfTableById(Long id) {
        return orderRepository.getAllOrderByIdTable(id);
    }

    @Override
    public void cancelTable(Long id) {
        orderRepository.removeOrderToTable(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findOrderById(id);
    }


}
