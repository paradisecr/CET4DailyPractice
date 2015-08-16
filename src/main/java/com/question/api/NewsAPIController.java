package com.question.api;

import java.util.List;

import com.question.sdk.result.BaseResult;
import com.question.sdk.result.NewsResult;
import com.question.server.NewsServer;


public class NewsAPIController extends BaseAPIController{
	
	public void todayNewsList(){
		String newsType = getPara("newsType");
		if(null == newsType){
			BaseResult result = failedResult();
			result.setMsg("缺少参数");
			renderJson(result);
			return;
		}
		NewsResult result = NewsServer.getTodayNews(newsType);
		renderJson(result);
		return;
	}
	
	public void index(){
		renderText("testIndex");
	}

}
