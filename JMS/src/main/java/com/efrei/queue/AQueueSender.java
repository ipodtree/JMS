package com.efrei.queue;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

public class AQueueSender{
	
	private QueueConnectionFactory connectionFactory;
	QueueSender sender;
	QueueSession session;
	QueueConnection connection;
	Queue queue;

	public AQueueSender(QueueConnectionFactory connectionFactory, Queue queue) throws Exception{
		super();
		this.connectionFactory = connectionFactory;
		this.queue = queue;
	}

	public void sendMessage(String message) throws JMSException {
		connection=connectionFactory.createQueueConnection();
		connection.start();
		session=connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		sender=session.createSender(queue);
		 for (int i = 1; i <= 1; i++) {
		TextMessage m=session.createTextMessage(message);
		System.out.println("ActiveMq send messages" + i+":"+message);
		sender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		sender.setTimeToLive(1000);
		sender.send(m);
		 }
		
		
		}
	
	public void close() throws JMSException {
		sender.close();
        session.close();
        connection.close();
    }
	
}
