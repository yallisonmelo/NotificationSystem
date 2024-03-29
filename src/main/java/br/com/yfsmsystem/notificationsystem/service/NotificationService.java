package br.com.yfsmsystem.notificationsystem.service;

import br.com.yfsmsystem.notificationsystem.components.SQSComponent;
import br.com.yfsmsystem.notificationsystem.converters.NotificationConverter;
import br.com.yfsmsystem.notificationsystem.dto.NotificationInputDto;
import br.com.yfsmsystem.notificationsystem.dto.NotificationOutputDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import br.com.yfsmsystem.notificationsystem.exception.NotificationNotFoundException;
import br.com.yfsmsystem.notificationsystem.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationConverter notificationConverter;
    private final  SQSComponent sqsComponent;

    public List<Notification> listAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification createNewNotification(NotificationInputDto notificationInputDto) {
        var notificationConverted = notificationConverter
                .convertToNotification(notificationInputDto);
        sqsComponent.sendSQSMessage(notificationInputDto);
        return notificationRepository
                .save(notificationConverted);
    }

    public NotificationOutputDto getNotificationById(Long id) {
        return notificationConverter.convertToNotificationOutputDto(
                notificationRepository.findById(id)
                        .orElseThrow(NotificationNotFoundException::new));
    }

    public void deleteNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(NotificationNotFoundException::new);
        notificationRepository.delete(notification);
    }

    public NotificationOutputDto alterNotification(NotificationInputDto notificationInputDto, Long id) {
        Notification notification = notificationConverter.convertToNotification(notificationInputDto);
        notification.setId(this.getNotificationById(id).getId());
        return notificationConverter
                .convertToNotificationOutputDto(
                        notificationRepository.save(notification));
    }
}
