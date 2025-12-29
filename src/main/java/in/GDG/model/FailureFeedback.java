package in.GDG.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "failure_feedback")
public class FailureFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contentId;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private String contentType; // PDF / YOUTUBE

    @Column(nullable = false)
    private String outcome; // FAILURE / SUCCESS

    private LocalDateTime submittedAt = LocalDateTime.now();

    // ---------- GETTERS ----------

    public Long getId() {
        return id;
    }

    public Long getContentId() {
        return contentId;
    }

    public String getSubject() {
        return subject;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getContentType() {
        return contentType;
    }

    public String getOutcome() {
        return outcome;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    // ---------- SETTERS (IMPORTANT) ----------

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
