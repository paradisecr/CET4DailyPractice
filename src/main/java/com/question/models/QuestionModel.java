package com.question.models;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Model;
import com.question.sdk.util.DateFormatUtil;
import com.question.util.IDUtil;

public class QuestionModel extends Model<QuestionModel>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707319241034721858L;

	public static QuestionModel dao = new QuestionModel();
	
	public static final String TABLE = "question";
	public static final String ID = "id";
	public static final String CONTENT = "content";
	public static final String ANSWER_A = "answer_a";
	public static final String ANSWER_B = "answer_b";
	public static final String ANSWER_C = "answer_c";
	public static final String ANSWER_D = "answer_d";
	public static final String CORRECT_ANSWER = "correct_answer";
	public static final String DATE = "date";
	public static final String OPERATE_TIME = "operate_time";
	
	public List<QuestionModel> getTodayQuestions(){
		
		String sql  = "select * from " + TABLE + " where " + DATE + " = ?";
		String dateStr = DateFormatUtil.formatDate(new Date());
		return find(sql, dateStr);
	}
	
	
	
	public boolean save(String content,String answerA,String answerB,String answerC,String answerD,
			int correctAnswer,Date date){
		QuestionModel model = new QuestionModel();
		model.set(ID, IDUtil.randomID());
		model.set(CONTENT, content);
		model.set(ANSWER_A, answerA);
		model.set(ANSWER_B, answerB);
		model.set(ANSWER_C, answerC);
		model.set(ANSWER_D, answerD);
		model.set(CORRECT_ANSWER, correctAnswer);
		model.set(DATE, date);
		model.set(OPERATE_TIME, new Date());
		return model.save();
	}
	
	public QuestionModel getById(String id){
		String sql  = "select * from " + TABLE + " where " + ID + " = ?";
		return findFirst(sql, id);
	}
	
	public boolean delete(String id){
		QuestionModel model = getById(id);
		if(null == model) return false;
		return model.delete();
	}
	
	public boolean update(String id,String content,String answerA,String answerB,String answerC,String answerD,
			int correctAnswer,Date date){
		QuestionModel model = getById(id);
		if(null == model) return false;
		model.set(CONTENT, OPERATE_TIME);
		model.set(ANSWER_A, answerA);
		model.set(ANSWER_B, answerB);
		model.set(ANSWER_C, answerC);
		model.set(ANSWER_D, answerD);
		model.set(CORRECT_ANSWER, correctAnswer);
		model.set(DATE, date);
		model.set(OPERATE_TIME, new Date());
		return model.update();
	}
}
