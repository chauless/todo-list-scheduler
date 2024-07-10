package pet.tasktrackerscheduler.scheduler.service.strategy;

import pet.tasktrackerscheduler.scheduler.model.Task;
import pet.tasktrackerscheduler.scheduler.model.User;

import java.sql.Timestamp;
import java.util.List;

public interface SummaryStrategy {
    List<Task> getTasks(User user, Timestamp start, Timestamp end);
}

