package projecta07.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projecta07.model.Feedback;

import java.util.List;
import java.util.Optional;

public interface IFeedbackService {
    List<Feedback> findAll();
    List<Feedback> findAllFeedbackByDateFeedback(String date);
    Optional<Feedback> findFeedbackById(Long id);
    void saveFeedback(Feedback feedback);
}
