package com.question.config;


import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.question.models.AdminModel;
import com.question.models.NewsModel;
import com.question.models.QuestionModel;
import com.question.route.ManageRoute;
import com.question.route.NewsAPIRoute;
import com.question.route.NewsManageRoute;
import com.question.route.QuestionAPIRoute;
import com.question.route.QuestionManageRoute;

public class ManagementConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		//加载配置
		loadPropertyFile("jfinal_config/global_config.properties");
		ResourceInterceptor.resroot = getProperty("resroot");
		me.setDevMode(true);
		me.setBaseViewPath("/pages");
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		// TODO Auto-generated method stub
		me.add(new ResourceInterceptor());
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		//配置c3p0插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password"));
		//((com.mchange.v2.c3p0.ComboPooledDataSource)c3p0Plugin.getDataSource()).setMaxStatements(0);
		me.add(c3p0Plugin);
		
		//配置ActiveRecord
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		//映射数据库
		arp.addMapping("news", NewsModel.class);
		arp.addMapping("question", QuestionModel.class);
		arp.addMapping("admin", AdminModel.class);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add(new NewsAPIRoute());
		me.add(new QuestionAPIRoute());
		me.add(new ManageRoute());
		me.add(new NewsManageRoute());
		me.add(new QuestionManageRoute());
	}

	public void afterJFinalStart(){
		//((com.mchange.v2.c3p0.ComboPooledDataSource)DbKit.getDataSource()).setMaxStatements(0);
	};
	
}
