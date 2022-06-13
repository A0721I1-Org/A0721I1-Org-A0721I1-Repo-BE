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

    @Override
    public Table updateTable(Table table) {
        //check2 ()
        //check3()
        return iTableRepository.save(table);
    }

    public Table save(Table table) {
        //check 1()
        // check 2()
        // check 3()
        return iTableRepository.save(table);
    }
    //check 1()
    // check 2()
    // check 3()
}
