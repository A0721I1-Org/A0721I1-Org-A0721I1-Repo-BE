package projecta07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Table;


@Repository
public interface ITableRepository extends JpaRepository<Table, Long> {
    //HuyNN search and paging query method
    @Query(value = "select id_table, code_table, empty_table, id_status from `Table`", nativeQuery = true)
    Page<Table> findAll(Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where id_status = :idStatus and empty_table = :emptyTable and code_table = :codeTable", nativeQuery = true)
    Page<Table> findAllByStatusAndEmptyTableAndCodeTable(Long idStatus, Boolean emptyTable, String codeTable, Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where id_status = :idStatus and empty_table = :emptyTable", nativeQuery = true)
    Page<Table> findAllByStatusAndEmptyTable(Long idStatus, Boolean emptyTable, Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where code_table = :codeTable and empty_table = :emptyTable", nativeQuery = true)
    Page<Table> findAllByCodeTableAndEmptyTable(String codeTable, Boolean emptyTable, Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where id_status = :idStatus and code_table = :codeTable", nativeQuery = true)
    Page<Table> findAllByStatusAndCodeTable(Long idStatus, String codeTable, Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where code_table = :codeTable", nativeQuery = true)
    Page<Table> findByCodeTable(String codeTable, Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where id_status = :idStatus", nativeQuery = true)
    Page<Table> findAllByStatus(Long idStatus, Pageable pageable);

    @Query(value = "select id_table, code_table, empty_table, id_status from `Table` where empty_table = :emptyTable", nativeQuery = true)
    Page<Table> findAllByEmptyTable(Boolean emptyTable, Pageable pageable);
}
