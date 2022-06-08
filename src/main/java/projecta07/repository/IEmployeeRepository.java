package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Employee;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
    @Query(value = "select * from employee  where id_employee =?1" , nativeQuery = true)
    Employee findEmployeeById(long id);
    @Query(value = "SELECT * FROM employee join user on employee.id_user = user.id_user where user.username like concat('%' ,?1,'%')and employee.name_employee like concat('%' ,?2,'%') and employee.phone_employee like concat('%' ,?3,'%')",nativeQuery = true)
    List<Employee> searchAllEmployee(String username, String name, String phone);
}
