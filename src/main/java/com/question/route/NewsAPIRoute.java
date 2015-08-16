package com.question.route;

import com.jfinal.config.Routes;
import com.question.api.NewsAPIController;

public class NewsAPIRoute extends Routes{

	@Override
	public void config() {
		// TODO Auto-generated method stub
		this.add("/api/news", NewsAPIController.class);
	}

}
