package com.question.route;

import com.jfinal.config.Routes;
import com.question.api.QuestionAPIController;

public class QuestionAPIRoute extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		this.add("/api/question", QuestionAPIController.class);
	}

}
