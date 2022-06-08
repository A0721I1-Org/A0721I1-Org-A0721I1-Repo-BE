package projecta07.service;

import projecta07.model.Employee;

import java.util.Optional;

public interface IEmployeeService {
    Iterable<Employee> findAll();
    void saveEmployee(Employee employee);
    Optional<Employee> findByIdEmployee(Long id);

}
