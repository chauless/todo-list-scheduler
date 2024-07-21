package pet.tasktrackerscheduler.scheduler.service;

import pet.tasktrackerscheduler.scheduler.dto.SummaryDto;

import java.sql.Timestamp;
import java.util.List;

public interface SummaryService {
    /**
     * This method is used to get a list of summaries for all users.
     * Each summary includes the count and titles of tasks completed and not completed by the user within a specified time period.
     *
     * @param now The current timestamp.
     * @param previous The timestamp from which the period starts.
     * @return A list of SummaryDto objects, each representing a summary for a user.
     */
    List<SummaryDto> getSummaryList(Timestamp now, Timestamp previous);
}
