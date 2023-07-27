package pet.tasktrackerscheduler.rabbitmq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import pet.tasktrackerscheduler.rabbitmq.dto.EmailDto;
import pet.tasktrackerscheduler.rabbitmq.service.RabbitMessageCreator;
import pet.tasktrackerscheduler.scheduler.dto.SummaryDto;

@Component
@RequiredArgsConstructor
public class RabbitMessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMessageCreator rabbitMessageCreator;
    private final ObjectMapper objectMapper;

    public void sendWelcomeEmail(String receiverEmail) throws JsonProcessingException {
        String queueName = QueueName.EMAIL_SENDER_TASKS.toString();
        EmailDto emailDto = rabbitMessageCreator.createWelcomeMessage(receiverEmail);
        sendEmail(emailDto, queueName);


    }

    public void sendSummaryEmail(SummaryDto summaryDto) {
        String queueName = QueueName.EMAIL_SENDER_TASKS.toString();
        EmailDto emailDto = rabbitMessageCreator.createSummaryMessage(summaryDto);
        sendEmail(emailDto, queueName);
    }


    protected void sendEmail(EmailDto emailDto, String queueName) {
        try {
            rabbitTemplate.convertAndSend(queueName, objectMapper.writeValueAsString(emailDto));
        } catch (Exception e) {

        }
    }
}