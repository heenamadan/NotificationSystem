
package com.target.notification.test;

import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import com.target.notification.service.NotificationService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotificationServiceTest {

    @Autowired
    private NotificationService service;
   // private ChannelFactory factory;
    
    KafkaTemplate<String, Message> kafkatemp;
    private String topic= "gen_notification_status";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       // service = new NotificationService();

        Message msg = generateMessage();
       // service = org.mockito.Mockito.mock(NotificationService.class);
//        Mockito.when(service.notify(ChannelType.slack, msg,kafkatemp, "gen_notification_status")).thenReturn(1L);
    }

    @Bean
    public ProducerFactory<String,Message> producerFactory(){
        Map<String,Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put("metadata.broker.list", "localhost:9092");
        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());


        return new DefaultKafkaProducerFactory(properties);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testNotifySlack() {
        Message msg = generateMessage();
        kafkatemp = new KafkaTemplate<String, Message>(producerFactory());

		Long id =service.notify(ChannelType.slack, msg,kafkatemp, topic);
		assertNotNull(id);

    }

    @Test
    public void testNotifyEmail() {
        Message msg = generateMessage();
        kafkatemp = new KafkaTemplate<String, Message>(producerFactory());
        assertThat(service.notify(ChannelType.email, msg,kafkatemp, topic), is(Long.class));
    }

    @Test
    public void testNotifyAll() {
        Message msg = generateMessage();
        kafkatemp = new KafkaTemplate<String, Message>(producerFactory());

        assertThat(service.notifyAll(msg,kafkatemp,topic), is(Long.class));
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
