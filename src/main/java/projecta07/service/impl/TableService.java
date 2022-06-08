package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Table;
import projecta07.repository.ITableRepository;
import projecta07.service.ITableService;

import java.util.List;

@Service
public class TableService implements ITableService {

    @Autowired
    private ITableRepository iTableRepository;

    @Override
    public List<Table> findAll() {
        return iTableRepository.findAll();
    }

    @Override
    public Table save(Table table) {
        return iTableRepository.save(table);
    }

}
