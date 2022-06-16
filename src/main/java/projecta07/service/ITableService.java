package projecta07.service;

import projecta07.dto.DetailOrderTableDTO;
import projecta07.model.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Status;
import projecta07.model.Table;

import java.util.List;

public interface ITableService {
    List<Table> findAll();
    Table updateTable(Table table);
    void deleteTableById(Long id);
    Table findTableById(Long id);
    Table save(Table table);
    //HuyNN search method
    List<Table> findAllByStatusAndEmptyTable(Long idStatus, Boolean emptyTable);
    List<Table> findByCodeTable(String codeTable);
    List<Table> findAllByStatus(Long idStatus);
    List<Table> findAllByEmptyTable(Boolean emptyTable);
}
