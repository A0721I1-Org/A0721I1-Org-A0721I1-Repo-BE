package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Table;

import java.util.List;

@Repository
public interface ITableRepository extends JpaRepository<Table, Long> {
}
