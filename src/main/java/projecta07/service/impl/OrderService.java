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

    @Override
    public Order saveOrder(Order order) {
        return iOrderService.save(order);
    }



}
