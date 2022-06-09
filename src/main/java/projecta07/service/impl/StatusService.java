package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Status;
import projecta07.repository.IStatusRepository;
import projecta07.service.IStatusService;

import java.util.List;

@Service
public class StatusService implements IStatusService {
    @Autowired
    private IStatusRepository iStatusRepository;
    @Override
    public List<Status> getAll() {
        return iStatusRepository.findAll();
    }

    @Override
    public Status getStatusById(Long id) {
        return iStatusRepository.findById(id).orElse(null);
    }

    @Override
    public Status updateStatus(Status status) {
        return iStatusRepository.save(status);
    }
}
