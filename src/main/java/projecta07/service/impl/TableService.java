package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Table;
import projecta07.repository.ITableRepository;
import projecta07.service.ITableService;

@Service
public class TableService implements ITableService {
    @Autowired
    private ITableRepository tableRepository;

    public Table saveTable(Table table) {
        return tableRepository.save(table);
    }

    public Table getTableById(Long id) {
        return tableRepository.findById(id).orElse(null);
    }
}
