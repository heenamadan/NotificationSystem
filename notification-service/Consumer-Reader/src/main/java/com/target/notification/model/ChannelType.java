package com.target.notification.model;
/**
 * Enum for channels type like email,slack
 * 
 */
public enum ChannelType {
    slack, email;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
