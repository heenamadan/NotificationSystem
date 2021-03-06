## Target Case Study

**Generic Notification System**   
This project demonstrate sample generic library for notification.

Following capabilities are supported:

1. Accept messages including from, to and subject
2. Ability to notify on multiple channels (email, slack)
3. Deliver messages in correct order

## Technologies

1. Spring Boot - 2.0
2. Jackson
3. Gradle
4. Swagger
5. simpleslackapi
6. spring-boot-email-tools
7. Kafka
8. MongoDB
9. Docker

## Installation

1) Without docker
i) Install MongoDB

Start MongoDb server:

To start MongoDB, run mongod.exe from the Command Prompt navigate to your MongoDB Bin folder and run mongod command, it will start MongoDB main process and The waiting for connections message in the console.

start mongo:
In another cmd, type: mongo to start mongo client

#create database to mongodb
use notification_system

ii) Install Kafka and Zookeeper 

start zookeeper

start kafka

iii) start producer:
<extract-path>\notification-service\producer to start the server manually.

Run `gradlew bootRun`

To Start Consumer:
<extract-path>\notification-service\consumer-reader to start the server manually.
Run `gradlew bootRun`


2) With Docker

1) Docker install
2) Docker start
3) Extract project to some <path>

Run docker-compose file. run below command under folder: <path>\notification-service\producer

docker-compose up -d

you can check docker-compose ps to show /check status of the containers

Note*- I am facing issues with mongodb while communicating via docker/application. Currently finding solution for the same.


## API Reference

**API 1: Notify to a Channel**

URL: `http://<HOST>/notifier/{channelType}/notify`

This sends given message to a specified channel like slack or email.
Where the `channelType` is slack, SMS or email.

e.g: `http://localhost:8080/api/v1.0/notifier/{channelType}/notify`   
with body as:
```javascript 
{  
   "body": "Body of the message",  
   "from": "sender@gmail.com",  
   "subject": "Notification Service Test Subject",  
   "to": "receiver@gmail.com"  
 }
```

**API 2: Notify All**

URL: `http://<HOST>/notifier/notifyAll`

This sends given message to all configured channels like slack and email.

e.g: `http://localhost:8080/api/v1.0/notifier/notifyAll`
with body as: 
```javascript 
{  
   "body": "Message Body",  
   "from": "sender@gmail.com",  
   "subject": "Notification Service Test Subject",  
   "to": "receiver@gmail.com"  
}

 
## Tests

Run `gradlew test` from console.

#properties

Change Email and Slack properties - application.properties.

Assumptions:

1) Dumped messages data to Kafka topic
2) Comsumer listen to topic and fetch records from topic.
3)Particular operation will be performed on basis of channel type. for eg: In case of email, email will be triggered and status will be updated. Similar for SMS and Slack channels.
4) As we are using Kafka, default history deletion is 7 days. Otherwise we can also change this time by configuring.
Default retention period for Segments is 7 days.
for changing to hours: set in your Kafka broker properties file:
log.retention.hours =168
which is 7 days and its default configuration.

 Scalability:

 Add partitions count to the topic
 Use cluster - Infrastructure


