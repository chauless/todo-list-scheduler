package pet.tasktrackerscheduler.rabbitmq.service;

import org.springframework.stereotype.Service;
import pet.tasktrackerscheduler.rabbitmq.dto.EmailDto;
import pet.tasktrackerscheduler.scheduler.dto.SummaryDto;

import java.util.List;

@Service
public class RabbitMessageCreator {

    public EmailDto createWelcomeMessage(String receiverEmail) {
        EmailDto emailDto = new EmailDto();

        emailDto.setReceiverEmail(receiverEmail);
        emailDto.setSubject("Welcome to Task Tracker");
        emailDto.setBody("Welcome to Task Tracker. We are happy to have you here. " + "We hope you will enjoy our service.");

        return emailDto;
    }

    public EmailDto createSummaryMessage(SummaryDto summary) {
        EmailDto emailDto = new EmailDto();

        String subject = "Summary";

        emailDto.setReceiverEmail(summary.getReceiverEmail());
        emailDto.setSubject(subject);

        if (summary.getCompletedTodayCount() != 0 && summary.getNotCompletedCount() != 0) {
            emailDto.setBody(getBodyForBoth(summary));

        } else if (summary.getCompletedTodayCount() != 0) {
            emailDto.setBody(getBodyForCompleted(summary));

        } else if (summary.getNotCompletedCount() != 0) {
            emailDto.setBody(getGetBodyForNotCompleted(summary));

        } else {
            emailDto.setBody(getBodyForBlank());

        }

        emailDto.setBody(emailDto.getBody().trim());

        return emailDto;
    }

    protected String getBodyForBlank() {
        String noTasks = "notasksmessage";
        return noTasks;
    }

    protected String getBodyForCompleted(SummaryDto summary) {
        String tasksCompleted = getTasksTitles(summary.getCompletedTodayTitles());

        return String.format("Good job! Today you completed %d tasks! Some of them: %s.",
                summary.getCompletedTodayCount(), tasksCompleted);
    }

    protected String getGetBodyForNotCompleted(SummaryDto summary) {
        String taskNotCompleted = getTasksTitles(summary.getNotCompletedTitles());

        return String.format("You have %d tasks to do! Some of them: %s.",
                summary.getNotCompletedCount(), taskNotCompleted);
    }

    protected String getBodyForBoth(SummaryDto summary) {

        String tasksCompleted = getTasksTitles(summary.getCompletedTodayTitles());
        String taskNotCompleted = getTasksTitles(summary.getNotCompletedTitles());

        return String.format("Good job! Today you completed %d tasks! Some of them: %s. But dont relax! " +
                "You also have %d tasks to do! Some of them: %s.",
                summary.getCompletedTodayCount(), tasksCompleted, summary.getNotCompletedCount(), taskNotCompleted);
    }

    protected String getTasksTitles(List<String> titles) {
        StringBuilder taskTitlesBuilder = new StringBuilder();

        for (String title : titles) {
            taskTitlesBuilder.append(title).append(", ");
        }
        return taskTitlesBuilder.substring(0, taskTitlesBuilder.length() - 2);
    }
}
