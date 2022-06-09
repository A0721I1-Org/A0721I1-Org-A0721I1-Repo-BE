package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee , Long> {
}
