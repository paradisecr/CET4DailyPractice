package com.question.api;

import com.question.sdk.result.QuestionResult;
import com.question.server.QuestionServer;

public class QuestionAPIController extends BaseAPIController{

	public void todayQuestionList(){
		QuestionResult result = QuestionServer.getTodayQuestionList();
		renderJson(result);
	}
	
}
