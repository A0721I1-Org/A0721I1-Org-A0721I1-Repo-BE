package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Employee;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
    //VinhTQ
    @Query(value = "select * from employee  where id_employee =?1", nativeQuery = true)
    Employee findEmployeeById(long id);

    //VinhTQ
    @Query(value = "SELECT  id_employee, name_employee, address_employee, date_of_birth_employee, gender_employee, phone_employee, salary_employee,position.id_position, position.name_position, \n " +
            "user.id_user,user.username, user.password, role.id_role,role.name_role " +
            "FROM employee \n" +
            "inner join position on employee.id_position = position.id_position \n" +
            "inner join user on employee.id_user = user.id_user \n " +
            "inner join userrole on user.id_user = userrole.id_user \n " +
            "inner join role on userrole.id_role = role.id_role \n " +
            "where user.username like concat('%' ,?1,'%') " +
            "and employee.name_employee like concat('%' ,?2,'%') " +
            "and employee.phone_employee like concat('%' ,?3,'%') ", nativeQuery = true)
    List<Employee> searchAllEmployee(String username, String name, String phone);

    //HauLST
    @Query(value = "select id_employee, name_employee, address_employee, date_of_birth_employee, gender_employee, phone_employee, salary_employee,position.id_position, position.name_position, \n" +
            "user.id_user,user.username, user.password, role.id_role,role.name_role\n" +
            "            from employee \n" +
            "            inner join position on employee.id_position = position.id_position\n" +
            "            inner join user on employee.id_user = user.id_user\n" +
            "            inner join userrole on user.id_user = userrole.id_user\n" +
            "            inner join role on userrole.id_role = role.id_role\n" +
            "            where employee.id_user=?1", nativeQuery = true)
    Employee findEmployeeByIdUser(Long idUser);
}
