package com.target.notification.db;

import com.target.notification.config.MongoConfig;
import com.target.notification.model.Message;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.FluentMongoOperations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
/**
 * Mongodb DAO class for mongo operations
 * */
public class NotificationMongoDao {
    /**
     * Returns mongooperation of mongotemplate
     * @return  {FluentMongoOperations}
     */
    private static FluentMongoOperations getMongoOperations(){
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        final FluentMongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        return mongoOperation;


    }
    /**
     * Fetch all records from mongo collection
     * @return list of messages {List} of {Message}
     */
    public static List<Message> selectAllRecords() {
        List<Message> messageList = ((MongoOperations) getMongoOperations()).findAll(Message.class);
        return messageList;
    }

    /**
     * Fetch all records from mongo collection
     * @param channel Type of String
     * @return list of messages {List} of {Message}
     */
    public static List<Message> selectChannelRecords(final String channel) {
        Query query = new Query(Criteria.where("ChannelType").is(channel));
        List<Message> messageList =  ((MongoOperations) getMongoOperations()).find(query, Message.class);
        return messageList;
    }
}
