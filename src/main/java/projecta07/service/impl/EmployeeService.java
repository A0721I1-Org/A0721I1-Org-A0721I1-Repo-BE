package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Employee;
import projecta07.repository.IEmployeeRepository;
import projecta07.service.IEmployeeService;


import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee findEmployeeByIdUser(Long IdUser) {
        return employeeRepository.findEmployeeByIdUser(IdUser);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Page<Employee> findAllPage(Pageable pageable) {
        return employeeRepository.findAll(pageable);
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
        return employeeRepository.searchAllEmployee(username, name, phone);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findByIdEmployee(Long id) {
        return employeeRepository.findById(id);
    }
}
