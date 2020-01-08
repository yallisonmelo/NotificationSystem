package br.com.yfsmsystem.notificationsystem.controller;

import br.com.yfsmsystem.notificationsystem.dto.NotificationDto;
import br.com.yfsmsystem.notificationsystem.service.NotificationService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {

    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity getListAllNotifications(Pageable pageable) {
        return ResponseEntity.ok(notificationService.listAllNotifications(pageable));
    }

    @GetMapping("/{id")
    public ResponseEntity getNotificationForId(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @PostMapping
    public ResponseEntity createNewNotification(@RequestBody @Valid NotificationDto notificationDto) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(notificationService.createNewNotification(notificationDto).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable Long id) {
        notificationService.deleteNotificationById(id);
        return ResponseEntity.noContent().build();
    }
}
