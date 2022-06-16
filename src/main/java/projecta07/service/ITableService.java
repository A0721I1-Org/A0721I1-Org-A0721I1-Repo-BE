package projecta07.service;

import projecta07.model.Table;

import java.util.List;

public interface ITableService {
    Table updateTable(Table table);
    void deleteTableById(Long id);
    Table findTableById(Long id);
    Table save(Table table);
    //HuyNN search and paging method
    List<Table> findAll();
    List<Table> findAllByStatusAndEmptyTable(Long idStatus, Boolean emptyTable);
    List<Table> findByCodeTable(String codeTable);
    List<Table> findAllByStatus(Long idStatus);
    List<Table> findAllByEmptyTable(Boolean emptyTable);
}
