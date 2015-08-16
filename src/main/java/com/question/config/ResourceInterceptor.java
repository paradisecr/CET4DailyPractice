package com.question.config;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ResourceInterceptor implements Interceptor{

	public static String resroot;
	
	public void intercept(ActionInvocation ai) {
		// TODO Auto-generated method stub
		ai.invoke();
		Controller c = ai.getController();
		c.setAttr("resroot", resroot);
	}

}
