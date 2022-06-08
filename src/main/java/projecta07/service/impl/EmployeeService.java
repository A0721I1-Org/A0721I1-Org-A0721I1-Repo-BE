package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Employee;
import projecta07.repository.IEmployeeRepository;
import projecta07.service.IEmployeeService;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public void deleteEmployee(long id) {
         employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> searchEmployee(String username, String name, String phone) {
        return employeeRepository.searchAllEmployee(username,name,phone);

    }
}
