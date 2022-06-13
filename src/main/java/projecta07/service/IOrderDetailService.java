package projecta07.service;

import projecta07.model.OrderDetail;

import java.util.Optional;

public interface IOrderDetailService {
    Optional<OrderDetail> findById(Long id);
}
