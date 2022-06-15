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
    private IEmployeeRepository iEmployeeService;

    @Override
    public List<Employee> findAll() {
        return iEmployeeService.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return iEmployeeService.findById(id).orElse(null);
    }


}
