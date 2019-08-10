package com.javalec.env_ps;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class Main {

	public static void main(String[] args) {

		//how to get external properties on an environment interface
		
		
		//get beans based on environment instance + external property sources
		System.out.println("========================================================================");
		System.out.println("environment instance + external property sources");
		
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		ConfigurableEnvironment env = ctx.getEnvironment();	//env is where itself scans instance property sources
		MutablePropertySources propertySources = env.getPropertySources();
		
		try {	//need try-catch as we are reading external files
			propertySources.addLast(new ResourcePropertySource("classpath:admin.properties"));
			System.out.println("admin ID: " + env.getProperty("admin.id"));
			System.out.println("admin PASSWORD: " + env.getProperty("admin.pw"));
			
		}catch (IOException e) {
			System.out.println("Check the admin file again.");
		}
		
		
		//get the same bean based on xml file		
		System.out.println("========================================================================");
		System.out.println("environment instance + xml (without initializig) + external property sources");

		GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext)ctx;	//container
		gCtx.load("applicationCTX.xml"); //set an environment. in the applicationCTX.xml, we only create the bean and don't initialize the field (properties)	 
		gCtx.refresh();	//we must do .refresh() to create a bean instance
		
		//the path to get properties on an environment interface
		//context => xml => adminConnection (ID) & AdminConnection (class) => env => admin.properties
		AdminConnection adminConnection = gCtx.getBean("adminConnectionID", AdminConnection.class);
		System.out.println("admin ID: " + adminConnection.getAdminId());
		System.out.println("admin PASSWORD: " + adminConnection.getAdminPw());
		
		gCtx.close();
		ctx.close();

	}

}
