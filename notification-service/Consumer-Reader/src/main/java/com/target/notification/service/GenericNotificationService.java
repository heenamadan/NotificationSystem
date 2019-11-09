package com.target.notification.service;
/**
 * Generic Notification service which is used to send notifications on basis of channel type
 * 
 */
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import com.target.notification.service.channel.ChannelFactoryClass;

import it.ozimov.springboot.mail.service.EmailService;

@Service
public class GenericNotificationService {

    private static final Logger LOG = LogManager.getLogger(GenericNotificationService.class);
    ChannelFactoryClass factory;

    /**
     * Notifies channel identified by given channelType with the given message.
     * @param msg The message includes from, to, subject, body
     */
    public long notifyAll(Message msg, EmailService emailService) {
        for(ChannelType type: ChannelType.values()) { 
        	LOG.info("sending email to channeltype{}",type);
            ChannelFactoryClass.getChannel(type).notify(msg, emailService);
            
        }
        return msg.getId();
    }

    /**
     * Notifies all configured channels(like slack and email) with the given message.
     * @param channelType Type of channel to notify - slack and email
     * @param msg The message includes from, to, subject, body
     */
    public long notify(ChannelType channelType, Message msg, EmailService emailService) {
    	LOG.info("sending email to channeltype{}",channelType);
    	ChannelFactoryClass.getChannel(channelType).notify(msg, emailService);
    	return msg.getId();
    }  
}
