package projecta07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projecta07.model.Feedback;
import projecta07.service.impl.FeedbackService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("manager/api/feedback")
@CrossOrigin(origins = "http://localhost:4200")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Feedback>> findAllFeedback() {
        List<Feedback> feedbackList = feedbackService.findAll();
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackList,HttpStatus.OK);
    }

    @GetMapping("/search/{date}")
    public ResponseEntity<Iterable<Feedback>> findAllFeedbackByDateFeedback(@PathVariable String date) {
        String dateSearch = date.replace("-","/");
        List<Feedback> feedbackListByDate = feedbackService.findAllFeedbackByDateFeedback(dateSearch);
        if (feedbackListByDate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackListByDate,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> findFeedbackById(@PathVariable Long id) {
        Optional<Feedback> feedbackOptional = feedbackService.findFeedbackById(id);
        if (!feedbackOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feedbackOptional.get(), HttpStatus.OK);
    }
}
