package com.question.management;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.question.api.BaseAPIController;
import com.question.sdk.bean.News;
import com.question.sdk.result.BaseResult;
import com.question.sdk.result.NewsResult;
import com.question.sdk.util.DateFormatUtil;
import com.question.server.NewsServer;

public class NewsManageController extends BaseAPIController{

	public void list(){
		String type = getPara("type","1");
		int start = getParaToInt("start",0);
		int num = getParaToInt("num",20);
		NewsResult result = NewsServer.newsList(type,start,num);
		List<News> newsList = result.getData();
		if(null == newsList){
			newsList = new ArrayList<News>();
		}
		String head = "资讯列表";
		if(type.equals("2")){
			head = "知识列表";
		}
		setAttr("head",head);
		setAttr("newsList", newsList);
		render("newsList.html");
	}
	
	public void preCreate(){
		String type = getPara("type","1");
		String head = "新建资讯";
		if(type.equals("2")){
			head = "新建列表";
		}
		setAttr("head",head);
		setAttr("type",type);
		render("newsCreate.html");
	}
	
	public void create(){
		int type = getParaToInt("type", 1);
		String title = getPara("title");
		String picUrl = getPara("picUrl","");
		String logoUrl = picUrl;
		String content = getPara("content","");
		String keyWord = getPara("keyWord","");
		String dateStr = getPara("dateStr","");
		Date date =  null;
		BaseResult result = null;
		if(dateStr.trim().equals("")){
			date = new Date();
		}else{
			try {
				date = DateFormatUtil.parseDate(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = failedResult();
				renderJson(result);
				return;
			}
		}
		boolean  flag = false;
		try{
			flag = NewsServer.create(title, keyWord, logoUrl, picUrl, content, type, date);
		}catch(Exception e){
			flag = false;
		}
		if(flag){
			result = successResult();
		}else{
			result = failedResult();
		}
		renderJson(result);
	}
	
	public void delete(){
		String id = getPara("id","");
		boolean flag = NewsServer.delete(id);
		BaseResult result = null;
		if(flag){
			result = successResult();
		}else{
			result = failedResult();
		}
		renderJson(result);
	}
	
	public void edit(){
		String id = getPara("id","");
		News news  = NewsServer.getQuestionById(id);
		if(news == null) {
			try {
				getResponse().sendRedirect("/m/welcome/index");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		setAttr("news",news);
		render("newsEdit.html");
		setAttr("id", id);
		return;
	}
	public void update(){
		int type = getParaToInt("type", 1);
		String title = getPara("title");
		String picUrl = getPara("picUrl","");
		String logoUrl = picUrl;
		String content = getPara("content","");
		String keyWord = getPara("keyWord","");
		String dateStr = getPara("dateStr","");
		String id = getPara("id","");
		Date date =  null;
		BaseResult result = null;
		if(dateStr.trim().equals("")){
			date = new Date();
		}else{
			try {
				date = DateFormatUtil.parseDate(dateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = failedResult();
				renderJson(result);
				return;
			}
		}
		boolean  flag = false;
		try{
			flag = NewsServer.update(id, title, keyWord, logoUrl, picUrl, content, type, date);
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		if(flag){
			result = successResult();
		}else{
			result = failedResult();
		}
		renderJson(result);
	}
}
