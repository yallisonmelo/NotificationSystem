package br.com.yfsmsystem.notificationsystem.service;

import br.com.yfsmsystem.notificationsystem.NotificationRepository;
import br.com.yfsmsystem.notificationsystem.components.ModelMapperComponent;
import br.com.yfsmsystem.notificationsystem.dto.NotificationDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import br.com.yfsmsystem.notificationsystem.exception.NotificationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    NotificationRepository notificationRepository;
    ModelMapperComponent modelMapperComponent;

    public NotificationService(NotificationRepository notificationRepository, ModelMapperComponent modelMapperComponent) {
        this.notificationRepository = notificationRepository;
        this.modelMapperComponent = modelMapperComponent;
    }


    public Page<Notification> listAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    public Notification createNewNotification(NotificationDto notificationDto) {
        Notification notification = modelMapperComponent.maping().map(notificationDto, Notification.class);
        return notificationRepository.save(notification);
    }

    public Optional<Notification> getNotificationById(Long id) {
        return Optional.ofNullable(notificationRepository.findById(id).orElseThrow(NotificationNotFoundException::new));
    }

    public void deleteNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(NotificationNotFoundException::new);
        notificationRepository.delete(notification);
    }
}
