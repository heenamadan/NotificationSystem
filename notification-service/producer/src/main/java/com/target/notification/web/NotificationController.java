package com.target.notification.web;
/**
 * Notification Controller class
 */
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.bind.annotation.*;

import com.target.notification.exception.BadRequest;
import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import com.target.notification.service.NotificationService;
import com.target.notification.util.EmailValidator;

@RestController
@RequestMapping("/api/v1.0/notifier")
@Api(value="Notification APIs")
public class NotificationController {

    private static final Logger LOG = LogManager.getLogger(NotificationController.class);
    @Autowired
    private NotificationService service;

    @Autowired
    EmailValidator emailValidator;
    
    @Autowired
    KafkaTemplate<String, Message> KafkaJsontemplate;
    
    private final String TOPIC_NAME = "gen_notification_status";
 

    @ApiOperation(value = "Notify the given message to given channelType.")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Not Found")})
    @PostMapping("/notify/{channelType}")
    public long notify(@PathVariable ChannelType channelType, @RequestBody Message msg) {
        if(ChannelType.email == channelType) {
            if(!emailValidator.isValid(msg.getFrom())) {
                throw new BadRequest("From Address", msg.getFrom());
            }
            if(!emailValidator.isValid(msg.getTo())) {
                throw new BadRequest("To Address", msg.getFrom());
            }
        }
        	
        
        return service.notify(channelType, msg, KafkaJsontemplate, TOPIC_NAME);
    }

    @ApiOperation(value = "Notify the given message to all channels like Slack and email.")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Not Found")})
    @PostMapping("/notifyAll")
    public long notifyAll(@RequestBody Message msg) {
        return service.notifyAll(msg, KafkaJsontemplate, TOPIC_NAME);
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

    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate(){
        return new KafkaTemplate<String, Message>(producerFactory());
    }
    
    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic(TOPIC_NAME, 3, (short) 1);
    }
}
