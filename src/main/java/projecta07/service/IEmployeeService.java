package projecta07.service;

import projecta07.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Employee findEmployeeByIdUser(Long IdUser);

    List<Employee> findAll();

    Employee findEmployeeById(long id);

    void deleteEmployee(long id);

    List<Employee> searchEmployee(String username, String name, String phone);

    void saveEmployee(Employee employee);

    Optional<Employee> findByIdEmployee(Long id);
}
