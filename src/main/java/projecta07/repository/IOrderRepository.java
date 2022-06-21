package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import projecta07.model.Order;
import projecta07.model.OrderDetail;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from `order` where status_order =0 and id_table = ?1" , nativeQuery = true)
    Order getOrderByTableId(Long id);

    @Query("select o from Order o where o.table.idTable = ?1 and o.statusOrder = false")
    Order getAllOrderByIdTable(Long id);

    /* @Query("delete order khỏi table ")*/
    @Modifying
    @Transactional
    @Query(value = "delete from `order` where `order`.id_table = ?1", nativeQuery = true)
    void removeOrderToTable(Long id);
}
