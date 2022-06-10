package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Order;
import projecta07.model.OrderDetail;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order,Long> {
    @Query("select o from Order o where o.table.idTable = ?1")
    Order getAllOrderByIdTable(Long id);
   /* @Query("update ")*/
    Order removeOrderByTable_IdTable(Long id);
}
