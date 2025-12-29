package in.GDG.dto;

public class FeedbackSummary {

    private long successCount;
    private long failureCount;
    private String verdict;

    // ✅ REQUIRED CONSTRUCTOR (THIS FIXES LINE 33)
    public FeedbackSummary(long successCount, long failureCount) {
        this.successCount = successCount;
        this.failureCount = failureCount;

        if (failureCount > successCount) {
            this.verdict = "RISKY";
        } else if (successCount > 0) {
            this.verdict = "TRUSTED";
        } else {
            this.verdict = "NEUTRAL";
        }
    }

    public long getSuccessCount() {
        return successCount;
    }

    public long getFailureCount() {
        return failureCount;
    }

    public String getVerdict() {
        return verdict;
    }
}
