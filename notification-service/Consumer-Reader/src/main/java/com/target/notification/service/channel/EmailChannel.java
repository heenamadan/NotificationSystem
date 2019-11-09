package com.target.notification.service.channel;
/**
 * Class which is used to send notification via Email
 * 
 */
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.target.notification.db.MongoDao;
import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import com.target.notification.model.Status;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;

@Service
public class EmailChannel implements Channel {

  

    @Value("${spring.mail.username}") String fromEmail;

    /**
     * Method to send email and save status to mongodb
     * 
     */
    @Override
    public void notify(Message msg, it.ozimov.springboot.mail.service.EmailService emailService) {
    	
       
        try {
            Email email = DefaultEmail.builder()
                    .from(new InternetAddress("heena.madan4@gmail.com"))
                    .to(Lists.newArrayList(new InternetAddress(
                            msg.getTo())))
                    .subject(msg.getSubject())
                    .body(msg.getBody())
                    .encoding("UTF-8").build();
                 
            emailService.send(email);
            msg.setStatus(Status.success.name());
            MongoDao.saveStatusToDB(msg);
        } catch (Exception e) {
        	msg.setStatus(Status.failure.name());
            MongoDao.saveStatusToDB(msg);
            throw new RuntimeException("Failed to send message using email channel, exception : "+e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(ChannelType channelType) {
        return ChannelType.email == channelType;
    }
}
