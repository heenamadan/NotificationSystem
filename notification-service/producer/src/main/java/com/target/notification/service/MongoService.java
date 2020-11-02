package com.target.notification.service;
/**
 * Notification service class
 */

import com.target.notification.db.NotificationMongoDao;
import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MongoService {

    private static final Logger LOG = LogManager.getLogger(MongoService.class);

    /**
     * Get all messages of channel type like email,sms, slack
     * @return list of messages {List} of {Message}
     */
    public List<Message> getAllMessages() {
        return NotificationMongoDao.selectAllRecords();

    }
    /**
     * Get messages as per channel type like email,sms, slack
     * @param channelType Type of chanel to notify - slack, SMS and email
     * @return list of messages {List} of {Message}
     */
    public List<Message> getChannelMessages(ChannelType channelType) {
        return NotificationMongoDao.selectAllRecords();

    }
}
