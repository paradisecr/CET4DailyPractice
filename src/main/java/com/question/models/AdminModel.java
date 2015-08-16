package com.question.models;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.question.sdk.util.DateFormatUtil;

public class AdminModel extends Model<AdminModel>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1337681875515630590L;

	public static AdminModel dao = new AdminModel();
	
	public static final String TABLE = "admin";
	public static final String USER_NAME = "user_name";
	public static final String PASSWORD = "password";
	public static final String TYPE = "type";
	public static final String LAST_LOGIN_TIME = "last_login_time";
	public static final String REGISTE_TIME = "registe_time";
	
	public AdminModel findAdmin(String userName,String password){
		String sql = "select * from " + TABLE + " where " + USER_NAME + " = ? && " + PASSWORD + "  = ?";
		return findFirst(sql,userName,password);
	}
	
}
