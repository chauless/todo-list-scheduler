package pet.tasktrackerscheduler.scheduler.service;

public interface SchedulerService {
    /**
     * This method is scheduled to run every day at midnight.
     * It retrieves a list of summaries for the past 24 hours and sends them as emails.
     *
     * The scheduling is done using the Spring's @Scheduled annotation with a cron expression.
     * The cron expression "0 0 0 * * *" represents every day at midnight.
     *
     */
    void sendSummaryEmails();
}
