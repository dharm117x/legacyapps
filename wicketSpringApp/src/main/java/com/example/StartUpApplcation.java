package com.example;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.example.page.HomePage;
import com.example.page.LoginPage;


public class StartUpApplcation extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}

	@Override
	protected void init() {
	    super.init();
	    // Connects Wicket components to Spring beans (@SpringBean)
	    getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	    mountPage("/login", LoginPage.class);
	    mountPage("/home", HomePage.class);
	}

}
