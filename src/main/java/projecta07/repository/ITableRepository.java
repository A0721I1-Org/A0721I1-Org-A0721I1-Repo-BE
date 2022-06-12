package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.dto.DetailOrderTableDTO;
import projecta07.model.Order;

import org.springframework.stereotype.Repository;

import projecta07.model.Table;


@Repository
public interface ITableRepository extends JpaRepository<Table,Long> {
    @Query(value = "select * from `table` where id_table = ?1" , nativeQuery = true)
    Table findTableById(Long id);
}
