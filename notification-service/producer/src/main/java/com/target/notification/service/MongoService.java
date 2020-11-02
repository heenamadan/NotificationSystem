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

    public List<Message> getAllMessages() {
        return NotificationMongoDao.selectAllRecords();

    }

    public List<Message> getChannelMessages(ChannelType channelType) {
        return NotificationMongoDao.selectAllRecords();

    }
    
   
    
    
    
}
