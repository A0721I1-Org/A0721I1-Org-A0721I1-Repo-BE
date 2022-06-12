package projecta07.service.impl;

import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.dto.DetailOrderTableDTO;
import projecta07.model.Table;
import projecta07.repository.ITableRepository;
import projecta07.service.ITableService;

import java.util.List;

@Service
public class TableService implements ITableService {
    @Autowired
    private ITableRepository tableRepository;

    @Override
    public List<Table> findAllEmptyTable() {
        return tableRepository.findAll();
    }

    @Override
    public Table save(Table table) {
        return tableRepository.save(table);
    }

    private ITableRepository iTableRepository;

    @Override
    public List<Table> getAll() {
       return iTableRepository.findAll();
    }
    @Override
    public List<Table> findAll() {
        return iTableRepository.findAll();
    }

    @Override
    public Table getTableById(Long id) {
        return iTableRepository.findById(id).orElse(null);
    }

    public Table findTableById(Long id) {
        return iTableRepository.findTableById(id);
    }


    @Override
    public Table updateTable(Table table) {
        return iTableRepository.save(table);
    }


}
