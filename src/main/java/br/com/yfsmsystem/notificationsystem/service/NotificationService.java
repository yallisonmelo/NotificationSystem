package br.com.yfsmsystem.notificationsystem.service;

import br.com.yfsmsystem.notificationsystem.converters.NotificationConverter;
import br.com.yfsmsystem.notificationsystem.dto.NotificationInputDto;
import br.com.yfsmsystem.notificationsystem.dto.NotificationOutputDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import br.com.yfsmsystem.notificationsystem.exception.NotificationNotFoundException;
import br.com.yfsmsystem.notificationsystem.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationConverter notificationConverter;


    public Page<Notification> listAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    public Notification createNewNotification(NotificationInputDto notificationInputDto) {
        return notificationRepository
                .save(notificationConverter
                        .convertToNotification(notificationInputDto));
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
