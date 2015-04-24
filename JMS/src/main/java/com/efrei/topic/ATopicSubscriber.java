package com.efrei.topic;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;




public class ATopicSubscriber {
	
	private TopicConnectionFactory connectionFactory;
	TopicSubscriber subscriber;
	TopicSession session;
	TopicConnection connection;
	Topic topic;
	
	public ATopicSubscriber(TopicConnectionFactory connectionFactory, Topic topic) throws Exception{
		super();
		this.connectionFactory = connectionFactory;
		this.topic = topic;
	}
	
	public void subscribeMessage(){
	
			try {
				connection=connectionFactory.createTopicConnection();
				connection.start();
				session=connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
				subscriber=session.createSubscriber(topic);
				subscriber.setMessageListener(new MessageListener() {
					
					@Override
					public void onMessage(Message message) {
						try {
							System.out.println("receive messages:"+((TextMessage) message).getText());
						} catch (JMSException e) {
							e.printStackTrace();
						}
						
					}
				});
				
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	

	 
	    public void close() throws Exception {
	       subscriber.close();
	       session.close();
	       connection.close();
	    }

}
