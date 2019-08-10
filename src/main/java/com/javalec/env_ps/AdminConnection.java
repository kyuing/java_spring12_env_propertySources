package com.javalec.env_ps;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

										//interfaces
public class AdminConnection implements EnvironmentAware, InitializingBean, DisposableBean {
	
	private Environment env;	//this is a key instance to set values from external sources and make "AdminConnection" class exist.
	private String adminId;
	private String adminPw;
	
	@Override
	public void setEnvironment(Environment env) {
		System.out.println("setEnvironment()");
		setEnv(env);
	}
	
	
	public void setEnv(Environment env) {
		this.env = env;		
	}


	public String getAdminId() {
		return adminId;
	}


	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	public String getAdminPw() {
		return adminPw;
	}


	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
		//each of property is injected from admin.properties
	
		setAdminId(env.getProperty("admin.id"));
		setAdminPw(env.getProperty("admin.pw"));
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("destroy()");
	}
	

}
