package com.question.server;

import static com.question.models.QuestionModel.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.question.models.QuestionModel;
import com.question.sdk.bean.AnswerEnum;
import com.question.sdk.bean.Question;
import com.question.sdk.result.QuestionResult;

public class QuestionServer {
	
	
	public static QuestionResult getTodayQuestionList(){
		List<QuestionModel> newsList = QuestionModel.dao.getTodayQuestions();
		QuestionResult result = new QuestionResult();
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
	
	public static boolean save(String content,String answerA,String answerB,String answerC,String answerD,
			int correctAnswer,Date date){
		return QuestionModel.dao.save(content, answerA, answerB, answerC, answerD, correctAnswer, date);
	}
	
	public static boolean update(String id,String content,String answerA,String answerB,String answerC,String answerD,
			int correctAnswer,Date date){
		return QuestionModel.dao.update(id, content, answerA, answerB, answerC, answerD, correctAnswer, date);
	}
	
	public static Question getQuestionById(String id){
		QuestionModel model = QuestionModel.dao.getById(id);
		if(model == null) return null;
		Question question =  transfer(model);
		return question;
	}
	
	public static boolean delete(String id){
		return QuestionModel.dao.delete(id);
	}
	
	public static List<Question> transfer(List<QuestionModel> modelList){
		List<Question> questionList = new ArrayList<Question>();
		for(QuestionModel model : modelList){
			questionList.add(transfer(model));
		}
		return questionList;
	}
	
	public static Question transfer(QuestionModel model){
		Question question = new Question();
		question.setId(model.getStr(ID));
		question.setContent(model.getStr(CONTENT));
		question.setAnswerA(model.getStr(ANSWER_A));
		question.setAnswerB(model.getStr(ANSWER_B));
		question.setAnswerC(model.getStr(ANSWER_C));
		question.setAnswerD(model.getStr(ANSWER_D));
		question.setCorrectAnswer(AnswerEnum.type(String.valueOf(model.getInt(CORRECT_ANSWER))));
		question.setDate(model.getDate(DATE));
		question.setOperateTime(model.getDate(OPERATE_TIME));
		return question;
	}
}
