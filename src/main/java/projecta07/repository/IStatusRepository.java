package projecta07.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Status;

@Repository
public interface IStatusRepository extends JpaRepository<Status,Long> {
}
