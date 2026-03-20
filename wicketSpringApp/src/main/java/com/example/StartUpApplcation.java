package com.example;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

import com.example.config.AppSession;
import com.example.page.HomePage;
import com.example.page.LoginPage;
import com.example.page.user.UserPage;


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
	    new AnnotatedMountScanner().scanPackage("com.example.page").mount(this);
	    mountPage("/login", LoginPage.class);
		mountPage("/home", HomePage.class);
	    mountPage("/user", UserPage.class);
	    
	}

	@Override
	public Session newSession(Request request, Response response) {
	    return new AppSession(request);
	}
}
