package com.target.notification.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
public class MongoConfig {

	@Value("${spring.data.mongodb.host}")
    private String mongoHost;

	public @Bean
    MongoTemplate mongoTemplate() throws Exception {
		
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(mongoHost, 27017),"notification");
		return mongoTemplate;
		
	}
		
}