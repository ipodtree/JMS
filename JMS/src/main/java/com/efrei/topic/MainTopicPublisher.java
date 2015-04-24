package com.efrei.topic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTopicPublisher {

	public static void main(String[] args) {

		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");

			ATopicPublisher publisher = (ATopicPublisher) applicationContext.getBean("topicPublisher");
			
			publisher.publishMessage("Coucou");
			publisher.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
