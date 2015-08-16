package com.question.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.question.models.AdminModel;
import com.question.models.NewsModel;
import com.question.sdk.bean.Admin;
import com.question.sdk.bean.News;
import com.question.sdk.bean.NewsTypeEnum;
import com.question.sdk.result.NewsResult;
import com.question.sdk.util.DateFormatUtil;

import static com.question.models.AdminModel.*;

public class AdminServer {

	public static Admin validateAdmin(String userName,String password){
		AdminModel adminModel = AdminModel.dao.findAdmin(userName, password);
		Admin admin = null;
		if(null != adminModel){
			admin = transfer(adminModel);
		}
		return admin;
	}
	

	
	public static Admin transfer(AdminModel model){
		Admin admin = new Admin();
		admin.setUserName(model.getStr(USER_NAME));
		admin.setType(String.valueOf(model.getInt(TYPE)));
		admin.setLastLoginTime(model.getDate(LAST_LOGIN_TIME));
		admin.setRegisteTime(model.getDate(REGISTE_TIME));
		return admin;
	}
}
