package in.GDG.repository;

import in.GDG.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findBySubjectAndPurpose(
            String subject,
            String purpose
    );

    List<Content> findBySubjectAndPurposeAndContentType(
            String subject,
            String purpose,
            String contentType
    );

    List<Content> findBySubjectAndPurposeAndContentTypeOrderByConfidenceScoreDesc(
            String subject,
            String purpose,
            String contentType
    );

    List<Content> findBySubjectAndPurposeAndContentTypeOrderByCredibilityScoreDesc(
            String subject,
            String purpose,
            String contentType
    );
}
