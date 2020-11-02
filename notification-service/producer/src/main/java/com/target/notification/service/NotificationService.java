package com.target.notification.service;
/**
 * Notification service class
 */
//import com.target.notification.db.NotificationMongoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.target.notification.model.ChannelType;

import com.target.notification.model.Message;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NotificationService {

    private static final Logger LOG = LogManager.getLogger(NotificationService.class);
   
    private AtomicInteger notificationId = new AtomicInteger(1);

   
    /**
     * Notifies channel identified by given channelType with the given message.
     * @param msg The message includes from, to, subject, body
     */
    public long notifyAll(Message msg, KafkaTemplate<String, Message> KafkaJsontemplate, String TOPIC_NAME) {
    	for(ChannelType type: ChannelType.values()) {
    		msg.setChannelType(type.name());
    	
            msg.setId(notificationId.getAndIncrement());
           
            KafkaJsontemplate.send(TOPIC_NAME,msg);
        }
        return notificationId.longValue();
    }

    /**
     * Notifies all configured channels(like SMS, slack and email) with the given message.
     * @param channelType Type of chanel to notify - slack, SMS and email
     * @param msg The message includes from, to, subject, body
     */
    public long notify(ChannelType channelType, Message msg, KafkaTemplate<String, Message> KafkaJsontemplate, String TOPIC_NAME) {
        msg.setId(notificationId.getAndIncrement());
        msg.setChannelType(channelType.name());
        KafkaJsontemplate.send(TOPIC_NAME,msg);
        return notificationId.longValue();
    }
}
