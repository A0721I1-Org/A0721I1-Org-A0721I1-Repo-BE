package projecta07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from `order` where date_order=:dateOrder", nativeQuery = true)
    Page<Order> findByDateOrder(Optional<String> dateOrder, Pageable pageable);

    @Query(value = "select * from `order` where date_order=:dateOrder and id_order=:idOrder", nativeQuery = true)
    Page<Order> findByIdOrderAndDateOrder(Optional<Long> idOrder, Optional<String> dateOrder, Pageable pageable);

    @Query(value = "select * from `order` where  id_order=:idOrder", nativeQuery = true)
    Page<Order> findByIdOrder(Optional<Long> idOrder, Pageable pageable);


}
