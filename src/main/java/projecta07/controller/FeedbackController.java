package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import projecta07.model.Feedback;
import projecta07.service.impl.FeedbackService;

@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/createFeedback")
    public ResponseEntity<Feedback> createFeedback(@Validated @ModelAttribute("feedBack") Feedback feedback, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
