package com.target.notification.db;

import com.target.notification.config.MongoConfig;
import com.target.notification.model.Message;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.FluentMongoOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class NotificationMongoDao {

    private static FluentMongoOperations getMongoOperations(){
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        final FluentMongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        return mongoOperation;


    }

    public static List<Message> selectAllRecords() {
        List<Message> messageList = ((MongoOperations) getMongoOperations()).findAll(Message.class);
        return messageList;
    }

    public static List<Message> selectChannelRecords(final String channel) {
        Query query = new Query(Criteria.where("ChannelType").is(channel));
        List<Message> messageList =  ((MongoOperations) getMongoOperations()).find(query, Message.class);
        return messageList;
    }
}
