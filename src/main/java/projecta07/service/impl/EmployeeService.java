package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projecta07.exception.EmployeeNotFoundException;
import projecta07.model.Employee;
import projecta07.repository.IEmployeeRepository;
import projecta07.service.IEmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepos;
    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepos.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepos.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepos.save(employee);
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if(employee != null)
        {
            employeeRepos.save(employee);
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteEmployee(Long id) {

        if(getEmployeeById(id)!= null)
        {
            employeeRepos.deleteById(id);
            return true;
        }
        return false;
    }

    // Ham kiem tra email nguoi dung
    @Override
    public Employee getByEmail(String email) {
        return employeeRepos.findByEmail(email);
    }

    // Hàm 1
    public void updateResetPasswordToken(String token, String email) throws EmployeeNotFoundException{
        Employee e = employeeRepos.findByEmail(email);
        if (e != null) {
            e.setResetPasswordToken(token);
            employeeRepos.save(e);
        } else {
            throw new EmployeeNotFoundException("Could not find any employee with the email " + email);
        }
    }
    // Hàm 2
    public Employee getByResetPasswordToken(String token) {
        return employeeRepos.findByResetPasswordToken(token);
    }
    // Hàm 3
    public void updatePassword(Employee employee, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        employee.getUser().setPassword(encodedPassword);
// Sau khi đổi mật khẩu xong tiến hành reset token = null để đảm bảo bảo mật
        employee.setResetPasswordToken(null);
        employeeRepos.save(employee);
    }
}
