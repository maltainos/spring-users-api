package com.tccfinal.app.ws;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {
	
	private static ApplicationContext APPLICATION_CONTEXT;

	public void setApplicationContext(ApplicationContext applicationContext) 
			throws BeansException {
		APPLICATION_CONTEXT = applicationContext;
	}
	
	public static Object getBean(String beanName) {
		return APPLICATION_CONTEXT.getBean(beanName);
	}
}
