package pet.tasktrackerscheduler.rabbitmq.service;

import pet.tasktrackerscheduler.rabbitmq.dto.EmailDto;
import pet.tasktrackerscheduler.scheduler.dto.SummaryDto;

public interface RabbitMessageCreator {
    /**
     * Creates a welcome message for a new user.
     *
     * @param receiverEmail The email address of the receiver.
     * @return An EmailDto object containing the welcome message.
     */
    EmailDto createWelcomeMessage(String receiverEmail);

    /**
     * Creates a summary message based on the provided SummaryDto object.
     *
     * The summary message includes information about tasks completed and not completed.
     * The body of the message is determined based on the number of tasks completed and not completed.
     *
     * @param summary A SummaryDto object containing information about tasks.
     * @return An EmailDto object containing the summary message.
     */
    EmailDto createSummaryMessage(SummaryDto summary);
}
