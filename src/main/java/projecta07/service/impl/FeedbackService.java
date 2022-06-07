package projecta07.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projecta07.model.Feedback;
import projecta07.repository.IFeedbackRepository;
import projecta07.service.IFeedbackService;

@Service
public class FeedbackService  implements IFeedbackService{

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
