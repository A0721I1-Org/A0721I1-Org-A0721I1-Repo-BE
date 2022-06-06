package projecta07.service;

import projecta07.model.Table;

import java.util.List;

public interface ITableService {
    List<Table> findAll();

    Table save(Table table);
}
