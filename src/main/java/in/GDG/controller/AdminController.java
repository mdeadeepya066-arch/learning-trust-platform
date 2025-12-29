package in.GDG.controller;

import in.GDG.model.Content;
import in.GDG.model.FailureFeedback;
import in.GDG.repository.ContentRepository;
import in.GDG.repository.FailureFeedbackRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final ContentRepository contentRepo;
    private final FailureFeedbackRepository feedbackRepo;

    public AdminController(ContentRepository contentRepo, FailureFeedbackRepository feedbackRepo) {
        this.contentRepo = contentRepo;
        this.feedbackRepo = feedbackRepo;
    }

    // 📌 Summary of trust per Subject
    @GetMapping("/trust-summary")
    public List<Map<String, Object>> trustSummary() {
        return contentRepo.findAll().stream()
                .collect(Collectors.groupingBy(Content::getSubject))
                .entrySet().stream()
                .map(e -> {
                    long success = e.getValue().stream()
                            .mapToLong(c -> feedbackRepo.countByContentIdAndOutcome(c.getId(), "SUCCESS"))
                            .sum();
                    long failure = e.getValue().stream()
                            .mapToLong(c -> feedbackRepo.countByContentIdAndOutcome(c.getId(), "FAILURE"))
                            .sum();

                    return Map.<String, Object>of(
                            "subject", e.getKey(),
                            "success", success,
                            "failure", failure
                    );
                })
                .collect(Collectors.toList());
    }

    // 📌 Top 3 trusted contents
    @GetMapping("/top-trusted")
    public List<Map<String, Object>> topTrusted() {
        return contentRepo.findAll().stream()
                .filter(c -> c.getCredibilityScore() != null)
                .sorted((a, b) -> Double.compare(
                        b.getCredibilityScore(), a.getCredibilityScore()))
                .limit(3)
                .map(c -> Map.<String, Object>of(
                        "title", c.getTitle(),
                        "score", c.getCredibilityScore(),
                        "subject", c.getSubject()
                ))
                .collect(Collectors.toList());
    }

    // 📌 Recent failures (sorted by submitted time)
    @GetMapping("/recent-failures")
    public List<Map<String, Object>> recentFailures() {
        return feedbackRepo.findAll().stream()
                .sorted((a, b) -> b.getSubmittedAt().compareTo(a.getSubmittedAt()))
                .limit(5)
                .map(f -> Map.<String, Object>of(
                        "contentId", f.getContentId(),
                        "subject", f.getSubject(),
                        "submittedAt", f.getSubmittedAt().toString()
                ))
                .collect(Collectors.toList());
    }
}
