package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import projecta07.exception.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Employee;
import projecta07.repository.IEmployeeRepository;
import projecta07.service.IEmployeeService;

import java.util.List;
import java.util.Optional;

//Team Employee
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository employeeRepository;

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
    // Team Employee

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if (employee != null) {
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(Long id) {

        if (getEmployeeById(id) != null) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //BachLT: Phuc hoi mat khau
    // BachLT1 Ham kiem tra email nguoi dung
    @Override
    public Employee getByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }


    // BachLT1  Hàm 1
    public void updateResetPasswordToken(String token, String email) throws EmployeeNotFoundException {
        Employee e = employeeRepository.findByEmail(email);
        if (e != null) {
            e.setResetPasswordToken(token);
            employeeRepository.save(e);
        } else {
            throw new EmployeeNotFoundException("Lỗi: Không thể tìm thấy email của bạn đã đăng ký:" + email);
        }
    }

    //BachLT1  Hàm 2
    public Employee getByResetPasswordToken(String token) {
        return employeeRepository.findByResetPasswordToken(token);
    }

    //BachLT1  Hàm 3
    public void updatePassword(Employee employee, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        employee.getUser().setPassword(encodedPassword);
// Sau khi đổi mật khẩu xong tiến hành reset token = null để đảm bảo bảo mật
        employee.setResetPasswordToken(null);
        employeeRepository.save(employee);
    }
}
