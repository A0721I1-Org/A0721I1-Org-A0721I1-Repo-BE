package projecta07.service.impl;

import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.dto.DetailOrderTableDTO;
import projecta07.model.Status;
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
    public List<Table> getAll() {
        return tableRepository.findAll();
    }

    @Override
    public Table getTableById(Long id) {
        return tableRepository.findTableById(id);
    }

    @Override
    public Table save(Table table) {
        return tableRepository.save(table);
    }

    private ITableRepository iTableRepository;

    @Override
    public List<Table> findAll() {
        return tableRepository.findAll();
    }

    @Override
    public void deleteTableById(Long id) {
        iTableRepository.deleteById(id);
    }

    public Table findTableById(Long id) {
        return iTableRepository.findTableById(id);
    }


    @Override
    public Table updateTable(Table table) {
        return iTableRepository.save(table);
    }

    @Override
    public Page<Table> findAll(Pageable pageable) {
        return iTableRepository.findAll(pageable);
    }

    @Override
    public Page<Table> findAllByStatusAndEmptyTableAndCodeTable(Status status, Boolean emptyTable, String codeTable, Pageable pageable) {
        return iTableRepository.findAllByStatusAndEmptyTableAndCodeTable(status.getIdStatus(), emptyTable, codeTable, pageable);
    }

    @Override
    public Page<Table> findAllByStatusAndEmptyTable(Status status, Boolean emptyTable, Pageable pageable) {
        return iTableRepository.findAllByStatusAndEmptyTable(status.getIdStatus(), emptyTable, pageable);
    }

    @Override
    public Page<Table> findAllByCodeTableAndEmptyTable(String codeTable, Boolean emptyTable, Pageable pageable) {
        return iTableRepository.findAllByCodeTableAndEmptyTable(codeTable, emptyTable, pageable);
    }

    @Override
    public Page<Table> findAllByStatusAndCodeTable(Status status, String codeTable, Pageable pageable) {
        return iTableRepository.findAllByStatusAndCodeTable(status.getIdStatus(), codeTable, pageable);
    }

    @Override
    public Page<Table> findByCodeTable(String codeTable, Pageable pageable) {
        return iTableRepository.findByCodeTable(codeTable, pageable);
    }

    @Override
    public Page<Table> findAllByStatus(Status status, Pageable pageable) {
        return iTableRepository.findAllByStatus(status.getIdStatus(), pageable);
    }

    @Override
    public Page<Table> findAllByEmptyTable(Boolean emptyTable, Pageable pageable) {
        return iTableRepository.findAllByEmptyTable(emptyTable, pageable);
    }
}
