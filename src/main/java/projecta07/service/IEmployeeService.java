package projecta07.service;

import projecta07.model.Employee;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    Employee getEmployeeById(Long id);
}
