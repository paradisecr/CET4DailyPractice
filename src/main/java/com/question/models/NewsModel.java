package com.question.models;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.question.sdk.util.DateFormatUtil;
import com.question.util.IDUtil;

public class NewsModel extends Model<NewsModel>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4105177732716229075L;

	public static NewsModel dao = new NewsModel();
	
	public static final String TABLE = "news";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String KEY_WORDS = "key_words";
	public static final String LOGO_URL = "logo_url";
	public static final String PIC_URL = "pic_url";
	public static final String CONTENT = "content";
	public static final String TYPE = "type";
	public static final String DATE = "date";
	public static final String OPERATE_TIME = "operate_time";
	
	public List<NewsModel> getTodayNews(String newsType){
		String todayStr = DateFormatUtil.formatDate(new Date());
		String sql = "select * from " + TABLE + " where " + DATE + " = ? && " + TYPE + "  = ?";
		return find(sql,todayStr,newsType);
	}
	public List<NewsModel> getTodayNews(String newsType,int start,int num){
		String todayStr = DateFormatUtil.formatDate(new Date());
		String sql = "select * from " + TABLE + " where " + DATE + " = ? && " + TYPE + "  = ? " 
		+ " limit ?,?";
		return find(sql,todayStr,newsType,start,num);
	}
	
	public List<NewsModel> newsList(String newsType,int start,int num){
		String sql = "select * from " + TABLE + " where " + TYPE + "  = ? " 
		+ " order by " + DATE + " desc" + " limit ?,?";
		return find(sql,newsType,start,num);
	}
	
	public boolean createNews(String title,String keyWords,String logoUrl,String picUrl,
			String content,int type,Date date){
		//String operateTime = DateFormatUtil.formatDate(new Date());
		String id = IDUtil.randomID();
		NewsModel news = new NewsModel();
		news.set(ID, id);
		news.set(TITLE, title);
		news.set(KEY_WORDS, keyWords);
		news.set(LOGO_URL, logoUrl);
		news.set(PIC_URL, picUrl);
		news.set(CONTENT, content);
		news.set(TYPE, type);
		news.set(DATE, date);
		news.set(OPERATE_TIME, new Date());
		return news.save();
	}
	
//	public boolean saveOrUpdate(String id,String title,String keyWords,String logoUrl,String picUrl,
//			String content,String type,Date date){
//		NewsModel news = getById(id);
//		if(null == news) news =  new NewsModel();
//		
//		
//	}
	
	public boolean update(String id,String title,String keyWords,String logoUrl,String picUrl,
			String content,int type,Date date){
		NewsModel news = getById(id);
		if(null == news)  return false;
		news.set(TITLE, title);
		news.set(KEY_WORDS, keyWords);
		news.set(LOGO_URL, logoUrl);
		news.set(PIC_URL, picUrl);
		news.set(CONTENT, content);
		news.set(TYPE,type);
		news.set(DATE, date);
		news.set(OPERATE_TIME, new Date());
		return news.update();
	}
	
	public boolean delete(String id){
		NewsModel news = NewsModel.dao.getById(id);
		if(news == null) return false;
		return news.delete();
	}
	
	public NewsModel getById(String id){
		String sql = "select * from " + TABLE + " where " + ID + "=?";
		NewsModel news = findFirst(sql,id);
		return news;
	}
}
