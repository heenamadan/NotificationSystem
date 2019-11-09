package com.target.notification.model;
/**
 * Pojo class for Message
 * 
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notification_system")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private long id;
    private String subject;
    private String from;
    private String to;
    private String body;
    private String sentTime;
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	private String ChannelType;
    private String status;

    public String getChannelType() {
		return ChannelType;
	}

	public void setChannelType(String channelType) {
		ChannelType = channelType;
	}

	public Message() {
        this.sentTime = LocalDateTime.now().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }



    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", body='" + body + '\'' +
                ", sentTime='" + sentTime + '\'' +
                ", channelType='" + ChannelType + '\'' +
                '}';
    }
}

//db.createCollection("notification_system")



