package pet.tasktrackerscheduler.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDto {

    private String receiverEmail;

    private Integer completedTodayCount;
    private List<String> completedTodayTitles;

    private Integer notCompletedCount;
    private List<String> notCompletedTitles;

}