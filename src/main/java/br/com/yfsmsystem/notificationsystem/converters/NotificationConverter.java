package br.com.yfsmsystem.notificationsystem.converters;

import br.com.yfsmsystem.notificationsystem.dto.NotificationInputDto;
import br.com.yfsmsystem.notificationsystem.dto.NotificationOutputDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationConverter {

    public Notification convertToNotification(NotificationInputDto notificationInputDto) {
        return Notification.builder()
                .description(notificationInputDto.getDescription())
                .title(notificationInputDto.getTitle())
                .build();
    }

    public NotificationOutputDto convertToNotificationOutputDto(Notification notification) {
        return NotificationOutputDto.builder()
                .id(notification.getId())
                .description(notification.getDescription())
                .title(notification.getTitle())
                .publicationDate(notification.getPublicationDate())
                .build();
    }

}
