package com.efrei.queue;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class AQueueReceiver implements MessageListener{
	
	private QueueConnectionFactory connectionFactory;
	Queue queue;
	QueueReceiver receiver;
	QueueSession session;
	QueueConnection connection;

	public AQueueReceiver(QueueConnectionFactory connectionFactory, Queue queue) throws Exception{
		super();
		this.connectionFactory = connectionFactory;
		this.queue = queue;
		
	}
	
	public void receiveMessage(){
		
		try {
			
			connection=connectionFactory.createQueueConnection();
			session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			receiver=session.createReceiver(queue);
			receiver.setMessageListener(this);
			connection.start();
			
			
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void onMessage(Message message) {
		try {
			System.out.println("receive messages:"+((TextMessage) message).getText());
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
	}	
	public void close() throws JMSException {
		receiver.close();
        session.close();
        connection.close();
    }
}
