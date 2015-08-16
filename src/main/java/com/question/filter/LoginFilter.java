package com.question.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.question.sdk.bean.Admin;
public class LoginFilter implements Filter{

	private String[] noNeedUri = {"/m/welcome/login","/m/welcome/logout"};
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String requestUri = req.getRequestURI();
        if(isNeedCheck(requestUri)){
        	chain.doFilter(request, response);
        	return;
        }
        Admin admin = (Admin)req.getSession().getAttribute("admin");
        if(null == admin){
        	res.sendRedirect("/m/welcome/login");
        	return;
        }
        chain.doFilter(request, response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isNeedCheck(String requestUri){
		for(String str : noNeedUri){
			if(requestUri.startsWith(str)){
				return true;
			}
		}
		return false;
	}

}
