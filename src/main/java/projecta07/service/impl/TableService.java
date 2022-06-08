package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Table;
import projecta07.repository.ITableRepository;
import projecta07.service.ITableService;

import java.util.List;
import java.util.Optional;

@Service
public class TableService implements ITableService{
    @Autowired
    private ITableRepository iTableRepository;

    public List<Table> findAll() {
        return iTableRepository.findAll();
    }

    @Override
    public void deleteTableById(Long id) {
        iTableRepository.deleteById(id);
    }

    @Override
    public Optional<Table> findTableById(Long id) {
        return iTableRepository.findById(id);
    }
}
