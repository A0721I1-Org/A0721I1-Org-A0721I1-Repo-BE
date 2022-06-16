package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Table;
import projecta07.repository.ITableRepository;
import projecta07.service.ITableService;

import java.util.List;

@Service
public class TableService implements ITableService{
    @Autowired
    private ITableRepository iTableRepository;

    @Override
    public void deleteTableById(Long id) {
        iTableRepository.deleteById(id);
    }

    @Override
    public Table findTableById(Long id) {
        return iTableRepository.findById(id).orElse(null);
    }

    @Override
    public Table updateTable(Table table) {
        return iTableRepository.save(table);
    }

    @Override
    public Table save(Table table) {
        return iTableRepository.save(table);
    }

    @Override
    public List<Table> findAll() {
        return iTableRepository.findAll();
    }

    @Override
    public List<Table> findAllByStatusAndEmptyTable(Long idStatus, Boolean emptyTable) {
        return iTableRepository.findAllByStatusAndEmptyTable(idStatus, emptyTable);
    }

    @Override
    public List<Table> findByCodeTable(String codeTable) {
        return iTableRepository.findByCodeTable(codeTable);
    }

    @Override
    public List<Table> findAllByStatus(Long idStatus) {
        return iTableRepository.findAllByStatus(idStatus);
    }

    @Override
    public List<Table> findAllByEmptyTable(Boolean emptyTable) {
        return iTableRepository.findAllByEmptyTable(emptyTable);
    }


}
