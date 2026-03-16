package com.example.page;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.example.config.AppSession;
import com.example.service.AuthService;

public class LoginPage extends TemplatePage {
	private static final long serialVersionUID = 1L;

    @SpringBean
    private AuthService authService;

	private String username;
    private String password;

    public LoginPage() {
    	// 1. Add the feedback panel to the page
    	add(new FeedbackPanel("feedback"));
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        // Get your custom session
   	 	AppSession session = (AppSession) getSession();
        // If the user is already logged in, send them to the Home Page
        if (session.isSignedIn()) {
            setResponsePage(HomePage.class);
        }
    }

    
    @Override
    protected void onInitialize() {
    	super.onInitialize();
        StatelessForm<LoginPage> form = new StatelessForm<LoginPage>("loginForm", new CompoundPropertyModel<>(this)) {
            @Override
            protected void onSubmit() {
                 if (authService.authenticate(username, password)) {
                	 AppSession session = (AppSession) getSession();
                	 session.setUsername(username);
                	 session.bind();
                     setResponsePage(HomePage.class);
                 } else {
                     error("Invalid username or password");
                 }
            }
        };

        form.add(new TextField<>("username").setRequired(true));
        form.add(new PasswordTextField("password").setRequired(true));
        add(form);
    }
}
