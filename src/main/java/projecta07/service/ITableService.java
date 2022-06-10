package projecta07.service;


import projecta07.dto.DetailOrderTableDTO;
import projecta07.model.Order;
import projecta07.model.Table;

import java.util.List;

public interface ITableService {
    List<Table> findAllEmptyTable();
    void save(Table table);
}
