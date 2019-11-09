package com.target.notification.model;
/**
 * Enum for channels like slack, email
 * 
 */
public enum ChannelType {
    slack, email ;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
