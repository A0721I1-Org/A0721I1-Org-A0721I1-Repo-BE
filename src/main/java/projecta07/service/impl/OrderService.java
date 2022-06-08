package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Order;
import projecta07.repository.IOrderRepository;
import projecta07.service.IOrderService;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    public Order getOrderByTableId(Long id) {
        return orderRepository.getOrderByTableId(id);
    }
}
