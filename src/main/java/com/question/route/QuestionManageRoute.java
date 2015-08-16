package com.question.route;

import com.jfinal.config.Routes;
import com.question.api.NewsAPIController;
import com.question.management.ManageController;
import com.question.management.QuestionManageController;

public class QuestionManageRoute extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		this.add("/m/question", QuestionManageController.class,"/question");
	}

}
