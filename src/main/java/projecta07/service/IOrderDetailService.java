package projecta07.service;

import projecta07.model.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    List<OrderDetail> findById(Long id);
}
