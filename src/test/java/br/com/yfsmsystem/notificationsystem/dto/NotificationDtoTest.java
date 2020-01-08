package br.com.yfsmsystem.notificationsystem.dto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NotificationDtoTest {

    private final static String DESCRIPTION = "test description";
    private final static String TITLE = "test title";
    private NotificationDto notificationDto;

    @Before
    public void Setup() {
        notificationDto = new NotificationDto();
        notificationDto.setDescription(DESCRIPTION);
        notificationDto.setTitle(TITLE);
    }

    @Test
    public  void getTitle() {
        Assert.assertEquals(TITLE,notificationDto.getTitle());
    }

    @Test
    public void getDescription() {
        Assert.assertEquals(DESCRIPTION,notificationDto.getDescription());
    }

    @Test
    public  void setTitle() {
        notificationDto.setTitle(TITLE);
        Assert.assertEquals(TITLE,notificationDto.getTitle());
    }

    @Test
    public  void setDescription() {
        notificationDto.setDescription(DESCRIPTION);
        Assert.assertEquals(DESCRIPTION,notificationDto.getDescription());
    }
}