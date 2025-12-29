package in.GDG.controller;

import in.GDG.dto.RecommendationResponse;
import in.GDG.model.Content;
import in.GDG.repository.FailureFeedbackRepository;
import in.GDG.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recommendations")
@CrossOrigin(origins = "*")
public class RecommendationController {

    private final RecommendationService service;
    private final FailureFeedbackRepository feedbackRepo;

    public RecommendationController(RecommendationService service,
                                    FailureFeedbackRepository feedbackRepo) {
        this.service = service;
        this.feedbackRepo = feedbackRepo;
    }

    @GetMapping
    public List<RecommendationResponse> getRecommendations(
            @RequestParam String subject,
            @RequestParam String purpose,
            @RequestParam String contentType,
            @RequestParam boolean isPrivate
    ) {

        List<Content> result =
                service.recommend(subject, purpose, contentType, isPrivate);

        return result.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Convert Content → RecommendationResponse
    private RecommendationResponse mapToResponse(Content content) {

        long success = feedbackRepo.countByContentIdAndOutcome(content.getId(), "SUCCESS");
        long failure = feedbackRepo.countByContentIdAndOutcome(content.getId(), "FAILURE");

        String verdict = getVerdict(success, failure);
        String reason = getTrustReason(content); // 🔥 FIXED HERE

        Double score = content.getCredibilityScore() != null
                ? content.getCredibilityScore()
                : content.getConfidenceScore() != null
                    ? content.getConfidenceScore()
                    : 0.0;

        return new RecommendationResponse(
                content.getId(),
                content.getTitle(),
                content.getSubject(),
                content.getPurpose(),
                content.getContentType(),
                score,
                content.getSource(),
                success,
                failure,
                verdict,
                reason
        );
    }

    private String getVerdict(long success, long failure) {
        if (success > failure) return "TRUSTED";
        if (success == 0 && failure == 0) return "UNVERIFIED";
        return "RISKY";
    }

    private String getTrustReason(Content content) {
        Double score = content.getCredibilityScore();

        if (score == null) {
            return "No feedback yet — learning in progress";
        } else if (score >= 70) {
            return "Trusted! Students succeeded using this content 🎯";
        } else if (score >= 40) {
            return "Moderate — use with caution ⚠️";
        } else {
            return "Risky — students struggled with this content ❌";
        }
    }
}
