package com.question.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.question.models.NewsModel;
import com.question.sdk.bean.News;
import com.question.sdk.bean.NewsTypeEnum;
import com.question.sdk.result.NewsResult;
import com.question.sdk.util.DateFormatUtil;

import static com.question.models.NewsModel.*;

public class NewsServer {

	public static NewsResult getTodayNews(String newsType){
		List<NewsModel> newsList = NewsModel.dao.getTodayNews(newsType);
		NewsResult result = new NewsResult();
		if(null == newsList || newsList.size() == 0){
			result.setCode(200);
			result.setMsg("成功");
			result.setCount(0);
		}else{
			result.setCode(200);
			result.setMsg("成功");
			result.setData(transfer(newsList));
			result.setCount(newsList.size());
		}
		return result;
	}
	public static NewsResult getTodayNews(String newsType,int start,int num){
		List<NewsModel> newsList = NewsModel.dao.getTodayNews(newsType,start,num);
		NewsResult result = new NewsResult();
		if(null == newsList || newsList.size() == 0){
			result.setCode(200);
			result.setMsg("成功");
			result.setCount(0);
		}else{
			result.setCode(200);
			result.setMsg("成功");
			result.setData(transfer(newsList));
			result.setCount(newsList.size());
		}
		return result;
	}
	
	public static boolean create(String title,String keyWords,String logoUrl,String picUrl,
			String content,int type,Date date){
		
		return NewsModel.dao.createNews(title, keyWords, logoUrl, picUrl, content, type, date);
	}
	
	public static boolean delete(String id){
		return NewsModel.dao.delete(id);
	}
	
	public static boolean update(String id,String title,String keyWords,String logoUrl,String picUrl,
			String content,int type,Date date){
		return NewsModel.dao.update(id, title, keyWords, logoUrl, picUrl, content, type, date);
	}
	
	public static News getQuestionById(String id){
		NewsModel model = NewsModel.dao.getById(id);
		if(null == model) return null;
		News news = transfer(model);
		return news;
	}
	
	public static NewsResult newsList(String newsType,int start,int num){
		List<NewsModel> newsList = NewsModel.dao.newsList(newsType,start,num);
		NewsResult result = new NewsResult();
		if(null == newsList || newsList.size() == 0){
			result.setCode(200);
			result.setMsg("成功");
			result.setCount(0);
		}else{
			result.setCode(200);
			result.setMsg("成功");
			result.setData(transfer(newsList));
			result.setCount(newsList.size());
		}
		return result;
	}
	
	public static List<News> transfer(List<NewsModel> modelList){
		List<News> newsList = new ArrayList<News>();
		for(NewsModel model : modelList){
			newsList.add(transfer(model));
		}
		return newsList;
	}
	
	public static News transfer(NewsModel model){
		News news = new News();
		news.setId(model.getStr(ID));
		news.setTitle(model.getStr(TITLE));
		news.setLogoUrl(model.getStr(LOGO_URL));
		news.setPicUrl(model.getStr(LOGO_URL));
		news.setContent(model.getStr(CONTENT));
		news.setType(NewsTypeEnum.type(String.valueOf(model.getInt(TYPE))));
		news.setDate(model.getDate(DATE));
		news.setKeyWords(model.getStr(KEY_WORDS));
		news.setOperateTime(model.getDate(OPERATE_TIME));
		return news;
	}
}
