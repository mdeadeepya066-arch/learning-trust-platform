package in.GDG.repository;

import in.GDG.model.FailureFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureFeedbackRepository
        extends JpaRepository<FailureFeedback, Long> {

    // ✅ already used by SubjectTrustService
    long countBySubjectAndPurposeAndOutcome(
            String subject,
            String purpose,
            String outcome
    );

    // ✅ REQUIRED for feedback summary (this fixes line 33)
    long countByContentIdAndOutcome(
            Long contentId,
            String outcome
    );
}
