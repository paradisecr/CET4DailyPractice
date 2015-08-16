package com.question.management;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.question.api.BaseAPIController;
import com.question.sdk.bean.Question;
import com.question.sdk.result.BaseResult;
import com.question.sdk.result.QuestionResult;
import com.question.sdk.util.DateFormatUtil;
import com.question.server.NewsServer;
import com.question.server.QuestionServer;

public class QuestionManageController extends BaseAPIController{

	public void list(){
		
		QuestionResult result = QuestionServer.getTodayQuestionList();
		List<Question> list = result.getData();
		if(list == null){
			list = new ArrayList<Question>();
		}
		setAttr("questionList", list);
		setAttr("head", "题目列表");
		render("questionList.html");
	}
	
	public void preCreate(){
		setAttr("head", "新建题目");
		render("questionCreate.html");
	}
	
	public void create(){
		String content = getPara("content","");
		String answerA = getPara("answerA","");
		String answerB = getPara("answerB","");
		String answerC = getPara("answerC","");
		String answerD = getPara("answerD","");
		int correctAnswer = getParaToInt("correctAnswer",1);
		String dateStr = getPara("date","");
		Date date = null;
		try {
			date = DateFormatUtil.parseDate(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = new Date();
		}
		boolean flag = QuestionServer.save(content, answerA, answerB, answerC, answerD, correctAnswer, date);
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
		if(id.trim().equals("")){
			try {
				getResponse().sendRedirect("/m/welcome/index");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Question question = QuestionServer.getQuestionById(id);
		if(question == null){
			try {
				getResponse().sendRedirect("/m/welcome/index");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setAttr("question", question);
		render("questionEdit.html");
		return;
	}
	
	public void update(){
		String id = getPara("id","");
		BaseResult result = null;
		if(id.trim().equals("")){
			result = failedResult();
			renderJson(result);
			return;
		}
		if(id.trim().equals("")){
			result = failedResult();
			renderJson(result);
			return;
		}
		String content = getPara("content","");
		String answerA = getPara("answerA","");
		String answerB = getPara("answerB","");
		String answerC = getPara("answerC","");
		String answerD = getPara("answerD","");
		int correctAnswer = getParaToInt("correctAnswer",1);
		String dateStr = getPara("date","");
		Date date = null;
		try {
			date = DateFormatUtil.parseDate(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = new Date();
		}
		boolean  flag = false;
		try{
			flag = QuestionServer.update(id, content, answerA, answerB, answerC, answerD, correctAnswer, date);
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
	
	public void delete(){
		String id = getPara("id","");
		BaseResult result = null;
		if(id.trim().equals("")){
			result = failedResult();
			renderJson(result);
			return;
		}
		boolean  flag = false;
		try{
			flag = QuestionServer.delete(id);
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
