package br.com.yfsmsystem.notificationsystem.components;

import br.com.yfsmsystem.notificationsystem.dto.NotificationInputDto;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SQSComponent {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.sqs-url}")
    private String sqsSend;

    public void sendSQSMessage(NotificationInputDto input) {
        queueMessagingTemplate.convertAndSend(sqsSend, input);
    }
}
