package com.target.notification.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import com.target.notification.service.GenericNotificationService;
/**
 * Receiver class which listens to kafka topic and get data and sends notification
 *  * 
 */

@Component
public class Receiver {
	
	@Autowired
	it.ozimov.springboot.mail.service.EmailService emailService;
	  
	@Autowired
	GenericNotificationService genericService;
    	
	@KafkaListener(topics = "gen_notification_status")
    public void receive(@Payload Message data) {
		
		System.out.println(data.getFrom());
		System.out.println(data.getSubject());
		System.out.println(data.getBody());
		
		ChannelType channel = Enum.valueOf(ChannelType.class, data.getChannelType()); 
		genericService.notify(channel, data, emailService);
    }
}
