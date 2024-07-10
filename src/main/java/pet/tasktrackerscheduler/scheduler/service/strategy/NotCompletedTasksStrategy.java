package pet.tasktrackerscheduler.scheduler.service.strategy;

import pet.tasktrackerscheduler.repository.TaskRepository;
import pet.tasktrackerscheduler.scheduler.model.Task;
import pet.tasktrackerscheduler.scheduler.model.User;

import java.sql.Timestamp;
import java.util.List;

public class NotCompletedTasksStrategy implements SummaryStrategy {

    private final TaskRepository taskRepository;

    public NotCompletedTasksStrategy(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks(User user, Timestamp start, Timestamp end) {
        return taskRepository.getTasksByUserAndCompleted(user, false);
    }
}
