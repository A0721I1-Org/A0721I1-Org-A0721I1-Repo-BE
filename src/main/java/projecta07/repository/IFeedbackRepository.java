package projecta07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projecta07.model.Feedback;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {
}
