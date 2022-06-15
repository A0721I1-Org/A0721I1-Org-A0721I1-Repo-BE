package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Status;
import projecta07.model.Table;
import projecta07.repository.ITableRepository;
import projecta07.service.ITableService;

import java.util.List;

@Service
public class TableService implements ITableService {
    @Autowired

    private ITableRepository iTableService;

    @Override
    public Table save(Table table) {
        return iTableService.save(table);
    }


    @Override
    public List<Table> findAll() {
        return iTableService.findAll();
    }

    @Override
    public void deleteTableById(Long id) {
        iTableService.deleteById(id);
    }

    public Table findTableById(Long id) {
        return iTableService.findTableById(id);
    }
    @Override
    public Table updateTable(Table table) {
        return iTableService.save(table);
    }


    @Override
    public Page<Table> findAll(Pageable pageable) {
        return iTableService.findAll(pageable);
    }

    @Override
    public Page<Table> findAllByStatusAndEmptyTableAndCodeTable(Status status, Boolean emptyTable, String codeTable, Pageable pageable) {
        return iTableService.findAllByStatusAndEmptyTableAndCodeTable(status.getIdStatus(), emptyTable, codeTable, pageable);
    }

    @Override
    public Page<Table> findAllByStatusAndEmptyTable(Status status, Boolean emptyTable, Pageable pageable) {
        return iTableService.findAllByStatusAndEmptyTable(status.getIdStatus(), emptyTable, pageable);
    }

    @Override
    public Page<Table> findAllByCodeTableAndEmptyTable(String codeTable, Boolean emptyTable, Pageable pageable) {
        return iTableService.findAllByCodeTableAndEmptyTable(codeTable, emptyTable, pageable);
    }

    @Override
    public Page<Table> findAllByStatusAndCodeTable(Status status, String codeTable, Pageable pageable) {
        return iTableService.findAllByStatusAndCodeTable(status.getIdStatus(), codeTable, pageable);
    }

    @Override
    public Page<Table> findByCodeTable(String codeTable, Pageable pageable) {
        return iTableService.findByCodeTable(codeTable, pageable);
    }

    @Override
    public Page<Table> findAllByStatus(Status status, Pageable pageable) {
        return iTableService.findAllByStatus(status.getIdStatus(), pageable);
    }

    @Override
    public Page<Table> findAllByEmptyTable(Boolean emptyTable, Pageable pageable) {
        return iTableService.findAllByEmptyTable(emptyTable, pageable);
    }

}
