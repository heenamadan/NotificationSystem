package com.target.notification.service.channel;

import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;

public interface Channel {
    default void notify(Message msg,it.ozimov.springboot.mail.service.EmailService emailService) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }

    default boolean supports(ChannelType type) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }
}
