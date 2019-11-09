
package target.notification.test;

import target.notification.model.ChannelType;
import target.notification.model.Message;
import target.notification.service.NotificationService;
import target.notification.service.channel.Channel;
import target.notification.service.channel.ChannelFactory;
import target.notification.service.channel.EmailChannel;
import target.notification.service.channel.SlackChannel;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotificationServiceTest {
    private NotificationService service;
    private ChannelFactory factory;
    
    KafkaTemplate<String, Message> kafkatemp;
    private String topic= "gen_notification_status";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new NotificationService(factory);
        kafkatemp = new KafkaTemplate<String, Message>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testNotifySlack() {
        Message msg = generateMessage();
        
		when(service.notify(ChannelType.slack, msg,kafkatemp)).thenReturn(anyLong());

        assertThat(service.notify(ChannelType.slack, msg,kafkatemp,topic), is(anyLong()));
    }

    @Test
    public void testNotifyEmail() {
        Message msg = generateMessage();
        when(service.notify(ChannelType.email, msg,kafkatemp)).thenReturn(anyLong());

        assertThat(service.notify(ChannelType.email, msg,kafkatemp,topic), is(anyLong()));
    }

    @Test
    public void testNotifyAll() {
        Message msg = generateMessage();
        when(service.notifyAll(msg,kafkatemp)).thenReturn(anyLong());

        assertThat(service.notifyAll(msg,kafkatemp,topic), is(Long.class));
    }

    @Test(expected = RuntimeException.class)
    public void testEmailMessageInvalid() {
        Message msg = generateMessage();
        msg.setFrom("invalid_mail_format");
        when(service.notify(ChannelType.email, msg,kafkatemp)).thenReturn(anyLong());

        assertThat(service.notify(ChannelType.email, msg,kafkatemp,topic), is(anyLong()));
    }

    private Message generateMessage() {
        Message msg = new Message();
        msg.setFrom("sender@gmail.com");
        msg.setTo("receiver@gmail.com");
        msg.setSubject("Test Subject  - Unit Test");
        msg.setBody("Body of Message");
        return msg;
    }
}
