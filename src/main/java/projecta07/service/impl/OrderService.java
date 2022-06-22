package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Order;
import projecta07.repository.IOrderRepository;
import projecta07.service.IOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findOrderByIdOrder(Optional<Long> idOrder, Pageable pageable) {
        return orderRepository.findByIdOrder(idOrder, pageable);
    }

    @Override
    public Page<Order> findOrderByDateOrder(Optional<String> dateOrder, Pageable pageable) {
        return orderRepository.findByDateOrder(dateOrder, pageable);
    }

    @Override
    public Page<Order> findOrderByIdOrderAndDateOrder(Optional<Long> idOrder, Optional<String> dateOrder, Pageable pageable) {
        return orderRepository.findByIdOrderAndDateOrder(idOrder, dateOrder, pageable);
    }
//ex
//    @Override
//    public List<Order> getAllOrder() {
//        return orderRepository.getAllOrder();
//    }

}
