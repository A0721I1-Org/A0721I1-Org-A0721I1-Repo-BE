package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projecta07.model.Feedback;
import projecta07.repository.IFeedbackRepository;
import projecta07.service.IFeedbackService;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService implements IFeedbackService {
    @Autowired
    private IFeedbackRepository iFeedbackRepository;

    @Override
    public List<Feedback> findAll() {
        return iFeedbackRepository.findAll();
    }

    @Override
    public List<Feedback> findAllFeedbackByDateFeedback(String date) {
        return iFeedbackRepository.findAllFeedbackByDateFeedback(date);
    }

    @Override
    public Optional<Feedback> findFeedbackById(Long id) {
        return iFeedbackRepository.findById(id);
    }
}
