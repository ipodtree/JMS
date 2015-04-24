package com.efrei.queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainQueueSender {

	public static void main(String[] args) {

		try{
			
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContextJMS.xml");

			AQueueSender sender = (AQueueSender) applicationContext.getBean("queueSender");
			
			sender.sendMessage("Coucou");
			sender.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
