package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Employee;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
//    @Query(value = "select id_employee,address_employee,date_of_birth_employee,gender_employee,name_employee,phone_employee,salary_employee from employee" , nativeQuery = true)
//    List<Employee> findAll();
    @Query(value = "select * from employee  where id_employee =?1" , nativeQuery = true)
    Employee findEmployeeById(long id);
    @Query(value = "select * from employee where user.username like concat('%' ,?1,'%')and name like concat('%' ,?2,'%') and phone like concat('%' ,?3,'%')",nativeQuery = true)
    List<Employee> searchAllEmployee(String username, String name, String phone);
}
