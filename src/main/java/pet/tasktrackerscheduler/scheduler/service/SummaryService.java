package pet.tasktrackerscheduler.scheduler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.tasktrackerscheduler.repository.TaskRepository;
import pet.tasktrackerscheduler.repository.UserRepository;
import pet.tasktrackerscheduler.scheduler.dto.SummaryDto;
import pet.tasktrackerscheduler.scheduler.model.Task;
import pet.tasktrackerscheduler.scheduler.model.User;
import pet.tasktrackerscheduler.scheduler.service.strategy.CompletedTasksStrategy;
import pet.tasktrackerscheduler.scheduler.service.strategy.NotCompletedTasksStrategy;
import pet.tasktrackerscheduler.scheduler.service.strategy.SummaryStrategy;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<SummaryDto> getSummaryList(Timestamp now, Timestamp previous) {
        List<SummaryDto> summaryList = new LinkedList<>();
        List<User> users = userRepository.findAll();

        SummaryStrategy completedTasksStrategy = new CompletedTasksStrategy(taskRepository);
        SummaryStrategy notCompletedTasksStrategy = new NotCompletedTasksStrategy(taskRepository);

        for (User user : users) {
            SummaryDto summaryDto = new SummaryDto();

            // Используем стратегию для получения выполненных задач
            List<Task> completedToday = completedTasksStrategy.getTasks(user, previous, now);
            List<String> completedTodayTitles = completedToday.stream()
                    .map(Task::getTitle)
                    .collect(Collectors.toList());
            Integer completedTodayCount = completedToday.size();

            // Используем стратегию для получения невыполненных задач
            List<Task> notCompleted = notCompletedTasksStrategy.getTasks(user, previous, now);
            List<String> notCompletedTitles = notCompleted.stream()
                    .map(Task::getTitle)
                    .collect(Collectors.toList());
            Integer notCompletedCount = notCompleted.size();

            // Заполняем SummaryDto
            summaryDto.setReceiverEmail(user.getUsername());
            summaryDto.setCompletedTodayCount(completedTodayCount);
            summaryDto.setCompletedTodayTitles(completedTodayTitles);
            summaryDto.setNotCompletedCount(notCompletedCount);
            summaryDto.setNotCompletedTitles(notCompletedTitles);

            summaryList.add(summaryDto);
        }
        return summaryList;
    }
}

