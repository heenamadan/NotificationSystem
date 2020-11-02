/*

package com.target.notification.test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import com.target.notification.service.GenericNotificationService;

import it.ozimov.springboot.mail.service.EmailService;



@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotificationServiceTest {
    private GenericNotificationService service;
    
    private String topic= "gen_notification_status";
    
    @Autowired
    EmailService emailService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
     
    }

    @After
    public void tearDown() throws Exception {
    }

   

    @Test
    public void testNotifyAll() {
    	Message msg = generateMessage();
        when(service.notifyAll( msg,emailService)).thenReturn(anyLong());

        assertThat(service.notifyAll(msg,emailService), is(anyLong()));
    }

    @Test
    public void testNotifyEmail() {
    	when(service.notify(ChannelType.email, generateMessage(),emailService)).thenReturn(anyLong());
        assertThat(service.notify(ChannelType.email, generateMessage(),emailService), is(Long.class));
    }

    @Test(expected = RuntimeException.class)
    public void testEmailMessageInvalid() {
        Message msg = generateMessage();
        msg.setFrom("invalid_mail_format");
        when(service.notify(ChannelType.email, msg,emailService)).thenReturn(anyLong());

        assertThat(service.notify(ChannelType.email, msg,emailService), is(anyLong()));
    }

    private Message generateMessage() {
        Message msg = new Message();
        msg.setFrom("sender@gmail.com");
        msg.setTo("receiver@gmail.com");
        msg.setSubject("Test Subject  - Unit Test");
        msg.setBody("Body of Message");
        msg.setId(1L);
        return msg;
    }
}
*/
