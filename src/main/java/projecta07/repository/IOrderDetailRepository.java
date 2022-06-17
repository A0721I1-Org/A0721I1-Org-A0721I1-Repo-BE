package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.OrderDetail;

import java.util.List;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query(value = "select * from orderdetail where id_order = ?1", nativeQuery = true)
    List<OrderDetail> getAllOrderDetailByOrderId(Long id);
}
