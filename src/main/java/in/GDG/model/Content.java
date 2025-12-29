package in.GDG.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Title given by user (e.g., "Java Collections Notes")
    @Column(nullable = false)
    private String title;

    // PDF, YOUTUBE, BLOG, AI_RESPONSE
    @Column(nullable = false)
    private String contentType;

    // URL for links OR file path for PDFs
    @Column(length = 1000)
    private String source;

    // EXAM, CAREER, PROJECT, REVISION
    @Column(nullable = false)
    private String purpose;

    // JAVA, DBMS, OS, etc.
    @Column(nullable = false)
    private String subject;

    // Indicates whether content is private (college notes, personal PDF)
    @Column(name = "is_private_content", nullable = false)
    private boolean privateContent = false;


    // Objective score (only when outcome data exists)
    private Double credibilityScore;

    // Preference-based score (for private or new content)
    private Double confidenceScore;

    // NEW, UNVERIFIED, VERIFIED
    private String verificationStatus;

    // When content was uploaded
    private LocalDateTime uploadedAt;

    // -------- Constructors --------

    public Content() {
        this.uploadedAt = LocalDateTime.now();
        this.verificationStatus = "NEW";
    }

    // -------- Getters and Setters --------

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isPrivateContent() {
        return privateContent;
    }

    public void setPrivateContent(boolean privateContent) {
        this.privateContent = privateContent;
    }


    public Double getCredibilityScore() {
        return credibilityScore;
    }

    public void setCredibilityScore(Double credibilityScore) {
        this.credibilityScore = credibilityScore;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
