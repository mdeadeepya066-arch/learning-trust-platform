package in.GDG.service;

import in.GDG.model.Content;
import in.GDG.repository.ContentRepository;
import in.GDG.repository.FailureFeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectTrustService {

    private final FailureFeedbackRepository feedbackRepo;
    private final ContentRepository contentRepo;

    public SubjectTrustService(
            FailureFeedbackRepository feedbackRepo,
            ContentRepository contentRepo) {
        this.feedbackRepo = feedbackRepo;
        this.contentRepo = contentRepo;
    }

    public void updateSubjectTrust(String subject, String purpose) {

        long failures =
                feedbackRepo.countBySubjectAndPurposeAndOutcome(
                        subject, purpose, "FAILURE");

        long successes =
                feedbackRepo.countBySubjectAndPurposeAndOutcome(
                        subject, purpose, "SUCCESS");

        // if failures are not dominating → no penalty
        if (failures <= successes) return;

        List<Content> contents =
                contentRepo.findBySubjectAndPurpose(subject, purpose);

        for (Content c : contents) {
            if (c.getCredibilityScore() == null) {
                c.setCredibilityScore(40.0);
            } else {
                c.setCredibilityScore(
                        Math.max(0, c.getCredibilityScore() - 10)
                );
            }
        }

        contentRepo.saveAll(contents);
    }
}
