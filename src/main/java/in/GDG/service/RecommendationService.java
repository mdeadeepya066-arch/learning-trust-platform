package in.GDG.service;

import in.GDG.model.Content;
import in.GDG.repository.ContentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationService {

    private final ContentRepository contentRepo;

    public RecommendationService(ContentRepository contentRepo) {
        this.contentRepo = contentRepo;
    }

    public List<Content> recommend(
            String subject,
            String purpose,
            String contentType,
            boolean isPrivate
    ) {
        if (isPrivate) {
            // Private → confidence based sorting
            return contentRepo.findBySubjectAndPurposeAndContentTypeOrderByConfidenceScoreDesc(
                    subject, purpose, contentType
            );
        }

        // Public → credibility scoring based
        return contentRepo.findBySubjectAndPurposeAndContentTypeOrderByCredibilityScoreDesc(
                subject, purpose, contentType
        );
    }
}
