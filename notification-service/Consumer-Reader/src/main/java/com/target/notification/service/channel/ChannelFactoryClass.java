package com.target.notification.service.channel;
/**
 * Factory Class
 * 
 */
import com.target.notification.model.ChannelType;

public class ChannelFactoryClass  {

	
	public static Channel getChannel(ChannelType name) {
		// TODO Auto-generated method stub
		
		if (ChannelType.email == name) {
            return new EmailChannel();
        } else if (ChannelType.slack == name) {
            return new SlackChannel();
        }
        return null;
	}


}
