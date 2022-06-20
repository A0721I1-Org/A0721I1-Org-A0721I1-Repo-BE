package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Order;
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

    public Order getOrderByTableId(Long id) {
        return orderRepository.getOrderByTableId(id);
    }

    public Order saveOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    @Autowired
    private IOrderRepository iOrderService;

    @Override
    public Order findOrderOfTableById(Long id) {
        return iOrderService.getAllOrderByIdTable(id);
    }

    @Override
    public void cancelTable(Long id) {
        iOrderService.removeOrderToTable(id);
    }

    @Override
    public List<Order> findAll() {
        return iOrderService.findAll();
    }
}
