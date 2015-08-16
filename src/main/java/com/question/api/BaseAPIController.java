package com.question.api;

import com.jfinal.core.Controller;
import com.question.sdk.base.AppBase;
import com.question.sdk.result.BaseResult;

public class BaseAPIController extends Controller{
	
	public static AppBase appBase;
	
	public static AppBase getAppBase(){
		return appBase;
	}
	
	public BaseResult failedResult(){
		BaseResult result = new BaseResult();
		result.setCode(-1);
		result.setMsg("服务器忙");
		return result;
	}
	
	public BaseResult successResult(){
		BaseResult result = new BaseResult();
		result.setCode(200);
		result.setMsg("成功");
		return result;
	}
	
}
