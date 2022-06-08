package projecta07.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import projecta07.model.Feedback;

import java.util.List;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {
    @Query (value="select * from feedback where date_feedback=?1",
            countQuery="select count (id) from feedback where date_feedback=?1",
            nativeQuery=true)
    List<Feedback> findAllFeedbackByDateFeedback(String date);
}
