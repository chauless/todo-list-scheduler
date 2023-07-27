package pet.tasktrackerscheduler.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.tasktrackerscheduler.repository.TaskRepository;
import pet.tasktrackerscheduler.repository.UserRepository;
import pet.tasktrackerscheduler.scheduler.dto.SummaryDto;
import pet.tasktrackerscheduler.scheduler.model.Task;
import pet.tasktrackerscheduler.scheduler.model.User;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<SummaryDto> getSummaryList(Timestamp now, Timestamp previous){
        List<SummaryDto> summaryList = new LinkedList<>();
        List<User> users = userRepository.findAll();

        for (User usr: users) {
            SummaryDto summaryDto = new SummaryDto();

            //getCompletedTodayTitles
            List<Task> completedToday = taskRepository.getTasksByUserAndCompletedAtBetween(usr, previous, now);
            List<String> completedTodayTitles = completedToday.stream()
                    .map(Task::getTitle)
                    .collect(Collectors.toList());
            Integer completedTodayCount = completedToday.size();

            //getNotCompletedTitles
            List<Task> notCompleted = taskRepository.getTasksByUserAndCompleted(usr, false);
            List<String> notCompletedTitles = notCompleted.stream()
                    .map(Task::getTitle)
                    .collect(Collectors.toList());
            Integer notCompletedCount = notCompleted.size();

            //getSummaryDto
            summaryDto.setReceiverEmail(usr.getUsername());
            summaryDto.setCompletedTodayCount(completedTodayCount);
            summaryDto.setCompletedTodayTitles(completedTodayTitles);
            summaryDto.setNotCompletedCount(notCompletedCount);
            summaryDto.setNotCompletedTitles(notCompletedTitles);

            summaryList.add(summaryDto);
        }
        return summaryList;
    }
}
