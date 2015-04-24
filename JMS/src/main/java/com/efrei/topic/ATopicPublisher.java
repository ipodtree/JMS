package com.efrei.topic;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;


public class ATopicPublisher {
	
	private TopicConnectionFactory connectionFactory;
	TopicPublisher publisher;
	TopicSession session;
	TopicConnection connection;
	Topic topic;

	public ATopicPublisher(TopicConnectionFactory connectionFactory, Topic topic) throws Exception{
		super();
		this.connectionFactory = connectionFactory;
		this.topic = topic;
	}

	public void publishMessage(String message) throws JMSException {
		connection=connectionFactory.createTopicConnection();
		connection.start();
		session=connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		publisher=session.createPublisher(topic);
		TextMessage m=session.createTextMessage(message);
		publisher.publish(m);
		System.out.println("ActiveMq publish messages" +":"+message);
		
	}
	
	public void close() throws Exception {
		publisher.close();
		session.close();
		connection.close();
	}

}
