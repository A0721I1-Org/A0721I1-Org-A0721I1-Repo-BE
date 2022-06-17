package projecta07.service;
import projecta07.model.Table;

import java.util.List;
public interface ITableService {
    List<Table> findAll();
    void deleteTableById(Long id);
    Table findTableById(Long id);
    Table save(Table table);
    //HuyNN search method
    List<Table> findAllByStatusAndEmptyTable(Long idStatus, Boolean emptyTable);
    List<Table> findByCodeTable(String codeTable);
    List<Table> findAllByStatus(Long idStatus);
    List<Table> findAllByEmptyTable(Boolean emptyTable);
}
