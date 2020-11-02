package com.target.notification.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
public class MongoConfig {

	public @Bean
    MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient("127.0.0.1"),"notification");
		return mongoTemplate;
		
	}
		
}