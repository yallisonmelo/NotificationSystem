package br.com.yfsmsystem.notificationsystem.service;

import br.com.yfsmsystem.notificationsystem.NotificationRepository;
import br.com.yfsmsystem.notificationsystem.dto.NotificationDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import br.com.yfsmsystem.notificationsystem.exception.NotificationNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationServiceTest {

    private Notification notification;
    @MockBean
    NotificationRepository notificationRepository;
    @Autowired
    NotificationService notificationService;

    @Before
    public void setUp() throws Exception {
        notification = new Notification();
        notification.setDescription("My Test");
        notification.setId(1l);
        notification.setPreviewDate(new Date());
        notification.setPublicationDate(new Date());
        notification.setTitle("My Title");
    }

    @Test
    public void TestlistAllNotifications() {
        Pageable pageable = PageRequest.of(0, 8);
        List<Notification> notifications = new ArrayList<>();
        notifications.add(notification);
        Page<Notification> page = new PageImpl(notifications);
        BDDMockito.given(notificationRepository.findAll(pageable)).willReturn(page);
        Assert.assertEquals(page, notificationService.listAllNotifications(pageable));
    }

    @Test
    public void createNewNotification() {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setDescription("My Test");
        notificationDto.setTitle("My Title");
        BDDMockito.given(notificationRepository.save(Mockito.any())).willReturn(notification);
        Assert.assertEquals(notification, notificationService.createNewNotification(notificationDto));
    }

    @Test
    public void TestgetNotificationByIdSucess() {
        BDDMockito.given(notificationRepository.findById(Mockito.anyLong())).willReturn(Optional.of(notification));
        Assert.assertEquals(Optional.of(notification), notificationService.getNotificationById(1L));
    }

    @Test(expected = NotificationNotFoundException.class)
    public void TestgetNotificationByIdException() {
        BDDMockito.given(notificationRepository.findById(Mockito.anyLong())).willReturn(Optional.<Notification>empty());
        notificationService.getNotificationById(1L);
    }


    @Test
    public void deleteNotificationById() {
        Optional<Notification> optionalNotification = Optional.of(notification);
        BDDMockito.given(notificationRepository.findById(1L)).willReturn(optionalNotification);
        notificationService.deleteNotificationById(notification.getId());
        Mockito.verify(notificationRepository, times(1)).delete(notification);
    }
}