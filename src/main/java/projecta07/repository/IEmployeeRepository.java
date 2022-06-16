package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("SELECT e FROM Employee e WHERE e.emailEmployee = ?1")
    public Employee findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.resetPasswordToken = ?1")
    public Employee findByResetPasswordToken(String token);
}
