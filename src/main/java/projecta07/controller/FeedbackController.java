package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Feedback;
import projecta07.service.impl.FeedbackService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/createFeedback")
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody Feedback feedback, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
