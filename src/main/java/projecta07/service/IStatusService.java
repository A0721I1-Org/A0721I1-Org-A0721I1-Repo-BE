package projecta07.service;

import projecta07.model.Status;

import java.util.List;

public interface IStatusService {
    List<Status> getAll();
    Status getStatusById(Long id);
    Status updateStatus(Status status);
    List<Status> findAll();
}
