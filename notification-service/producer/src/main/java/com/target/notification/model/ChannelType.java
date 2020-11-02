package com.target.notification.model;
/**
 * Enum for channels like slack, email, SMS
 * 
 */
public enum ChannelType {
    slack, SMS, email ;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
