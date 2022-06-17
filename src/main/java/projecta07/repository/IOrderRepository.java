package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projecta07.model.Order;
import projecta07.model.OrderDetail;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.table.idTable = ?1")
    Order getAllOrderByIdTable(Long id);

    /* @Query("delete order khỏi table ")*/
    @Modifying
    @Transactional
    @Query(value = "delete from `order` where `order`.id_table = ?1", nativeQuery = true)
    void removeOrderToTable(Long id);

}
