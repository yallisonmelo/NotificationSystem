package br.com.yfsmsystem.notificationsystem.controller;

import br.com.yfsmsystem.notificationsystem.dto.NotificationInputDto;
import br.com.yfsmsystem.notificationsystem.dto.NotificationOutputDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import br.com.yfsmsystem.notificationsystem.service.NotificationService;
import br.com.yfsmsystem.notificationsystem.shared.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(RestConstants.PATH + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_NOTIFICATION)
@RequiredArgsConstructor
@Api(value = "Api Notification - Documentation")
public class NotificationController {

    private final NotificationService notificationService;

    @ApiOperation(
            value = "List all Notification",
            response = NotificationOutputDto.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return List All Notification"),
            @ApiResponse(code = 404, message = "Not value in database")
    })
    @GetMapping
    public ResponseEntity<Page<Notification>> getListAllNotifications(Pageable pageable) {
        return ResponseEntity.ok(notificationService.listAllNotifications(pageable));
    }

    @ApiOperation(
            value = "Return Notification by id",
            response = NotificationOutputDto.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return Notification"),
            @ApiResponse(code = 404, message = "Notification not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NotificationOutputDto> getNotificationForId(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @ApiOperation(
            value = "Create new Notification",
            response = NotificationOutputDto.class,
            consumes = "application/json",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return Notification")
    })
    @PostMapping
    public ResponseEntity<URI> createNewNotification(@RequestBody @Valid NotificationInputDto notificationInputDto) {
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(notificationService.createNewNotification(notificationInputDto).getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @ApiOperation(
            value = "Delete Notification by id",
            response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted Notification"),
            @ApiResponse(code = 404, message = "Notification not found for delete")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable Long id) {
        notificationService.deleteNotificationById(id);
        return ResponseEntity.noContent().build();
    }
}
