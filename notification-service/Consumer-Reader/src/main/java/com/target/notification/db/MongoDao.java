package com.target.notification.db;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.target.notification.config.SpringMongoConfig;
import com.target.notification.model.Message;

public class MongoDao {

	public static void saveStatusToDB(Message message) {

		final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		final MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		mongoOperation.save(message, "generic_notify_status");
	}
}
