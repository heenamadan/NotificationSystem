package com.target.notification.service.channel;
/**
 * Factory Class
 * 
 */
import com.target.notification.model.ChannelType;

public class ChannelFactoryClass  {

	
	public static Channel getChannel(ChannelType name) {

		if (ChannelType.email == name) {
            return new EmailChannel();
        } else if (ChannelType.slack == name) {
            return new SlackChannel();
        } else if (ChannelType.SMS == name) {
            return new SMSChannel();
        }
        return new EmailChannel();
	}
}
