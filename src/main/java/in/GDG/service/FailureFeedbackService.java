package in.GDG.service;

import in.GDG.dto.FeedbackSummary;
import in.GDG.model.Content;
import in.GDG.model.FailureFeedback;
import in.GDG.repository.ContentRepository;
import in.GDG.repository.FailureFeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FailureFeedbackService {

    private final FailureFeedbackRepository feedbackRepo;
    private final ContentRepository contentRepo;

    public FailureFeedbackService(FailureFeedbackRepository feedbackRepo,
                                  ContentRepository contentRepo) {
        this.feedbackRepo = feedbackRepo;
        this.contentRepo = contentRepo;
    }

    public void submitFeedback(Long contentId, FailureFeedback feedback) {

        Content content = contentRepo.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        feedback.setContentId(contentId);
        feedback.setSubject(content.getSubject());
        feedback.setPurpose(content.getPurpose());
        feedback.setContentType(content.getContentType());

        feedbackRepo.save(feedback);

        // Auto adjust credibility score
        adjustScore(content, feedback.getOutcome());

        contentRepo.save(content);
    }

    private void adjustScore(Content content, String outcome) {
        Double score = content.getCredibilityScore();

        if (score == null) score = 50.0; // default neutral

        if ("SUCCESS".equalsIgnoreCase(outcome)) {
            score = Math.min(score + 10, 100);
        } else if ("FAILURE".equalsIgnoreCase(outcome)) {
            score = Math.max(score - 15, 0);
        }

        content.setCredibilityScore(score);
    }

    // Trust summary
    public FeedbackSummary getFeedbackSummary(Long contentId) {
        long success = feedbackRepo.countByContentIdAndOutcome(contentId, "SUCCESS");
        long failure = feedbackRepo.countByContentIdAndOutcome(contentId, "FAILURE");
        return new FeedbackSummary(success, failure);
    }
}
