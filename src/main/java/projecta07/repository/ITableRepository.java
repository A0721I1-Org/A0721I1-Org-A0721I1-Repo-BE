package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Table;

import java.util.List;

@Repository
public interface ITableRepository extends JpaRepository<Table,Long> {

    @Query(value = "select * from `table` where `table`.id_table = ?1" , nativeQuery = true)
    Table findTableById(Long id);

    //HuyNN search and paging query method
    @Query(value = "select id_table, code_table, empty_table, id_status from `Table`", nativeQuery = true)
    List<Table> findAll();

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where id_status = :idStatus and empty_table = :emptyTable", nativeQuery = true)
    List<Table> findAllByStatusAndEmptyTable(Long idStatus, Boolean emptyTable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where code_table like concat('%' ,:codeTable,'%')", nativeQuery = true)
    List<Table> findByCodeTable(String codeTable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where id_status = :idStatus", nativeQuery = true)
    List<Table> findAllByStatus(Long idStatus);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where empty_table  = :emptyTable", nativeQuery = true)
    List<Table> findAllByEmptyTable(Boolean emptyTable);
}
