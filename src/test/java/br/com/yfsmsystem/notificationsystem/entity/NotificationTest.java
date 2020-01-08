package br.com.yfsmsystem.notificationsystem.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationTest {

    private static final String DESCRIPTION = "My description";
    private static final String TITLE = "My title";
    private static final Long ID = 1L;
    private static final Date PREVIEWDATE = new Date();
    private static final Date PUBLICATIONDATE = new Date();


    private Notification notification;

    @Before
    public void setUp() throws Exception {
        notification = new Notification();
        notification.setTitle(TITLE);
        notification.setPublicationDate(PUBLICATIONDATE);
        notification.setPreviewDate(PREVIEWDATE);
        notification.setId(ID);
        notification.setDescription(DESCRIPTION);
    }

    @Test
    public void getId() {
        Assert.assertEquals(ID, notification.getId());
    }

    @Test
    public void getTitle() {
        Assert.assertEquals(TITLE, notification.getTitle());
    }

    @Test
    public void getDescription() {
        Assert.assertEquals(DESCRIPTION, notification.getDescription());
    }

    @Test
    public void getPublicationDate() {
        Assert.assertEquals(PUBLICATIONDATE, notification.getPublicationDate());
    }

    @Test
    public void getPreviewDate() {
        Assert.assertEquals(PREVIEWDATE, notification.getPreviewDate());
    }

    @Test
    public void setId() {
        notification.setId(ID);
        Assert.assertEquals(ID, notification.getId());
    }

    @Test
    public void setTitle() {
        notification.setTitle(TITLE);
        Assert.assertEquals(TITLE, notification.getTitle());
    }

    @Test
    public void setDescription() {
        notification.setDescription(DESCRIPTION);
        Assert.assertEquals(DESCRIPTION, notification.getDescription());
    }

    @Test
    public void setPublicationDate() {
        notification.setPublicationDate(PUBLICATIONDATE);
        Assert.assertEquals(PUBLICATIONDATE, notification.getPublicationDate());
    }

    @Test
    public void setPreviewDate() {
        notification.setPreviewDate(PREVIEWDATE);
        Assert.assertEquals(PREVIEWDATE, notification.getPreviewDate());
    }
}