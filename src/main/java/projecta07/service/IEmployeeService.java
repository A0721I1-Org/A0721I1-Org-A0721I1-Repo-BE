package projecta07.service;

import projecta07.model.Employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    Employee findEmployeeByIdUser(Long IdUser);

    //phương thức của bin
    Employee findEmployeeByUser(Long idUser);
    //
    List<Employee> findAll();

    Page<Employee> findAllPage(Pageable pageable);

    Employee findEmployeeById(long id);

    void deleteEmployee(long id);

    Page<Employee> searchEmployee(String username, String name, String phone,Pageable pageable);

    void saveEmployee(Employee employee);

    Optional<Employee> findByIdEmployee(Long id);
}
