package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Order;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from `order` where statusOrder=0 and id_table = ?1" , nativeQuery = true)
    Order getOrderByTableId(Long id);
}
