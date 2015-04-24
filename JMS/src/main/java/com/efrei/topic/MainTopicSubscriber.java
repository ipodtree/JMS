package com.efrei.topic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTopicSubscriber {

	public static void main(String[] args) {
		
		try{
		
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");
			ATopicSubscriber subscriber1 = (ATopicSubscriber) applicationContext.getBean("topicSubscriber1");
			ATopicSubscriber subscriber2 = (ATopicSubscriber) applicationContext.getBean("topicSubscriber2");
			
			subscriber1.subscribeMessage();
			subscriber2.subscribeMessage();
			Thread.sleep(10000);
			subscriber1.close();
			Thread.sleep(20000);
			subscriber2.close();

			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
