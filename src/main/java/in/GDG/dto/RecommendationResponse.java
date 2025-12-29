package in.GDG.dto;

public class RecommendationResponse {

    private Long contentId;
    private String title;
    private String subject;
    private String purpose;
    private String contentType;
    private Double score;
    private String source;

    private long successCount;
    private long failureCount;
    private String verdict;
    private String reason;

    public RecommendationResponse(Long contentId, String title, String subject,
                                  String purpose, String contentType, Double score,
                                  String source,
                                  long successCount, long failureCount,
                                  String verdict, String reason) {

        this.contentId = contentId;
        this.title = title;
        this.subject = subject;
        this.purpose = purpose;
        this.contentType = contentType;
        this.score = score;
        this.source = source;
        this.successCount = successCount;
        this.failureCount = failureCount;
        this.verdict = verdict;
        this.reason = reason;
    }

    public Long getContentId() { return contentId; }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public String getPurpose() { return purpose; }
    public String getContentType() { return contentType; } // 🔥 Fixed
    public Double getScore() { return score; }
    public String getSource() { return source; }
    public long getSuccessCount() { return successCount; }
    public long getFailureCount() { return failureCount; }
    public String getVerdict() { return verdict; }
    public String getReason() { return reason; }
}
