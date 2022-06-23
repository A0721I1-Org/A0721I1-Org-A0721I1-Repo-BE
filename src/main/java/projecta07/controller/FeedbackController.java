package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Feedback;
import projecta07.service.impl.FeedbackService;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("manager/api/feedback/")
    public ResponseEntity<Iterable<Feedback>> findAllFeedback(Pageable pageable) {
        Page<Feedback> feedbackList = feedbackService.findAll(pageable);
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("manager/api/feedback/search/{date}")
    public ResponseEntity<Iterable<Feedback>> findAllFeedbackByDateFeedback( @PathVariable String date,Pageable pageable)  {
        String dateSearch = date.replace("-","/");
        Page<Feedback> feedbackListByDate = feedbackService.findAllFeedbackByDateFeedback(dateSearch, pageable);
        if (feedbackListByDate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackListByDate,HttpStatus.OK);
    }

    @GetMapping("manager/api/feedback/view/{id}")
    public ResponseEntity<Feedback> findFeedbackById(@PathVariable Long id) {
        Optional<Feedback> feedbackOptional = feedbackService.findFeedbackById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feedbackOptional.get(), HttpStatus.OK);
    }

    @PostMapping("api/feedback/createFeedback")
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody Feedback feedback, BindingResult bindingResult) {
        String codeFeedback = "FB-" + Math.floor(Math.random()* 999);
        feedback.setCodeFeedback(codeFeedback);
        feedback.setDateFeedback(LocalDate.now());
        if (bindingResult.hasFieldErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
