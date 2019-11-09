package com.target.notification.service.channel;

import com.target.notification.model.ChannelType;

public abstract class AbstractFactory {
	
	abstract Channel getChannel(ChannelType name);

}
