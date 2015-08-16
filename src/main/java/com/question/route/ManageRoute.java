package com.question.route;

import com.jfinal.config.Routes;
import com.question.api.NewsAPIController;
import com.question.management.ManageController;

public class ManageRoute extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		this.add("/m/welcome", ManageController.class,"/welcome");
	}

}
