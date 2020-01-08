package br.com.yfsmsystem.notificationsystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = NotificationsystemApplicationTests.class)
public class NotificationsystemApplicationTests {

    @Test
    public void main() {
        NotificationsystemApplication.main(new String[]{});
    }

}
