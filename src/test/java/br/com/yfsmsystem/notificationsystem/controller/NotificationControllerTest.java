package br.com.yfsmsystem.notificationsystem.controller;

import br.com.yfsmsystem.notificationsystem.NotificationRepository;
import br.com.yfsmsystem.notificationsystem.dto.NotificationDto;
import br.com.yfsmsystem.notificationsystem.entity.Notification;
import br.com.yfsmsystem.notificationsystem.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class NotificationControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private NotificationService notificationService;

    @MockBean(name = "delete")
    private NotificationService notificationServiceTest;

    @MockBean
    NotificationRepository notificationRepository;


    private static final String URL_BASE = "/api/v1/notification/";
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
    public void getListAllNotifications() throws Exception {
        List<Notification> notificationList = new ArrayList<>();
        notificationList.add(notification);
        Page<Notification> pageNotifications = new PageImpl<>(notificationList);
        BDDMockito.given(notificationRepository.findAll(Mockito.any(Pageable.class))).willReturn(pageNotifications);
        mvc.perform(get(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].id").value(notification.getId()))
                .andExpect(jsonPath("$.content.[0].title").value(notification.getTitle()))
                .andExpect(jsonPath("$.content.[0].description").value(notification.getDescription()))
                .andExpect(jsonPath("$.content.[0].publicationDate").exists())
                .andExpect(jsonPath("$.content.[0].previewDate").exists());
    }

    @Test
    public void getNotificationForId() throws Exception {
        BDDMockito.given(notificationRepository.findById(Mockito.anyLong())).willReturn(Optional.of(notification));
        mvc.perform(get(URL_BASE + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(notification.getId()))
                .andExpect(jsonPath("$.title").value(notification.getTitle()))
                .andExpect(jsonPath("$.description").value(notification.getDescription()))
                .andExpect(jsonPath("$.publicationDate").exists())
                .andExpect(jsonPath("$.previewDate").exists());
    }

    @Test
    public void createNewNotification() throws Exception {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setTitle(TITLE);
        notificationDto.setDescription(DESCRIPTION);
        BDDMockito.given(notificationService.createNewNotification(notificationDto)).willReturn(notification);
        BDDMockito.given(notificationRepository.save(notification)).willReturn(notification);
        mvc.perform(post(URL_BASE)
                .content(objectMapper.writeValueAsString(notificationDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

    }

    @Test
    public void deleteNotificationById() throws Exception {
        Optional<Notification> optionalNotification = Optional.of(notification);
        BDDMockito.given(notificationRepository.findById(1L)).willReturn(optionalNotification);
        notificationServiceTest.deleteNotificationById(notification.getId());
        Mockito.verify(notificationServiceTest, times(1)).deleteNotificationById(Mockito.anyLong());
        mvc.perform(delete(URL_BASE + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }
}