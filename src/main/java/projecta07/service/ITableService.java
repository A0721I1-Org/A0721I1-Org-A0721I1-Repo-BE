package projecta07.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Status;
import projecta07.model.Table;

import java.util.List;
import java.util.Optional;

public interface ITableService {
    List<Table> findAll();
    void deleteTableById(Long id);
    Optional<Table> findTableById(Long id);
    Table save(Table table);

    Page<Table> findAll(Pageable pageable);
    Page<Table> findAllByStatusAndEmptyTableAndCodeTable(Status status, Boolean emptyTable, String codeTable, Pageable pageable);
    Page<Table> findAllByStatusAndEmptyTable(Status status, Boolean emptyTable, Pageable pageable);
    Page<Table> findAllByCodeTableAndEmptyTable(String codeTable, Boolean emptyTable, Pageable pageable);
    Page<Table> findAllByStatusAndCodeTable(Status status, String codeTable, Pageable pageable);
    Page<Table> findByCodeTable(String codeTable, Pageable pageable);
    Page<Table> findAllByStatus(Status status, Pageable pageable);
    Page<Table> findAllByEmptyTable(Boolean emptyTable, Pageable pageable);
}
