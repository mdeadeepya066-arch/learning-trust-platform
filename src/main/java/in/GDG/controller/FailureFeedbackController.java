package in.GDG.controller;

import in.GDG.model.FailureFeedback;
import in.GDG.dto.FeedbackSummary;
import in.GDG.service.FailureFeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*")
public class FailureFeedbackController {

    private final FailureFeedbackService service;

    public FailureFeedbackController(FailureFeedbackService service) {
        this.service = service;
    }

    // ✅ EXISTING: submit feedback
    @PostMapping("/{contentId}")
    public ResponseEntity<?> submitFeedback(
            @PathVariable Long contentId,
            @RequestBody FailureFeedback feedback) {

        service.submitFeedback(contentId, feedback);
        return ResponseEntity.ok().build();
    }

    // ✅ NEW: feedback summary (success/failure count + verdict)
    @GetMapping("/summary/{contentId}")
    public ResponseEntity<FeedbackSummary> getFeedbackSummary(
            @PathVariable Long contentId) {

        return ResponseEntity.ok(
                service.getFeedbackSummary(contentId)
        );
    }
}
