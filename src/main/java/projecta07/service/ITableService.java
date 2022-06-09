package projecta07.service;

import projecta07.model.Table;

import java.util.List;
import java.util.Optional;

public interface ITableService {
    List<Table> getAll();
    Table getTableById(Long id);
    Table updateTable(Table table);
}
