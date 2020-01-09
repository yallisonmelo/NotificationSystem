package br.com.yfsmsystem.notificationsystem.service;

import br.com.yfsmsystem.notificationsystem.NotificationRepository;
import br.com.yfsmsystem.notificationsystem.dto.NotificationDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import br.com.yfsmsystem.notificationsystem.exception.NotificationNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;


    private ModelMapper modelMapperComponent;

    public NotificationService() {
        modelMapperComponent = new ModelMapper();
    }


    public Page<Notification> listAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    public Notification createNewNotification(NotificationDto notificationDto) {
        Notification notification = modelMapperComponent.map(notificationDto, Notification.class);
        notification = notificationRepository.save(notification);
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
