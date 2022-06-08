package projecta07.service;

import projecta07.model.Employee;

import java.util.List;

public interface IEmployeeService {
List<Employee> findAll();
Employee findEmployeeById(long id);
void deleteEmployee(long id);

List<Employee> searchEmployee(String username, String name, String phone);
}
