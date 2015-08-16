package com.question.management;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.question.api.BaseAPIController;
import com.question.sdk.bean.Admin;
import com.question.server.AdminServer;

public class ManageController extends BaseAPIController{
	public void index(){
		render("index.html");
	}
	
	public void login() throws IOException{
		String userName = getPara("userName");
		String password = getPara("password");
		if(null == userName){
			render("login.html");
			return;
		}
		//登陆
		userName = URLDecoder.decode(userName,"utf-8");
		password = URLDecoder.decode(password,"utf-8");
		
		Admin admin = AdminServer.validateAdmin(userName, password);
		if(null == admin){
			render("login.html");
			return;
		}
		getSession().setAttribute("admin", admin);
		setAttr("admin",admin);
		getResponse().sendRedirect("/m/welcome/index");
	}
	
	public void logout() throws IOException{
		getSession().setAttribute("admin", null);
		getResponse().sendRedirect("/m/welcome/login");
	}
	
	public void tables(){
		render("tables.html");
	}
	public void forms(){
		render("forms.html");
	}
	public void panelsWells(){
		render("panelsWells.html");
	}
	public void notifications(){
		render("notifications.html");
	}
	public void typography(){
		render("typography.html");
	}
	public void icons(){
		render("icons.html");
	}
	public void blank(){
		render("blank.html");
	}
	public void grid(){
		render("grid.html");
	}
	public void flot(){
		render("flot.html");
	}
	public void morris(){
		render("morris.html");
	}
	
}
