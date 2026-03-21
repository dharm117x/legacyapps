package com.example.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.example.config.MenuItem;
import com.example.service.UserService;

public class HomePage extends TemplatePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserService userService;

    public HomePage() {
    	crumbs.add(new MenuItem("Home", HomePage.class));
        add(new Label("message", "Welcome to the Wicket 8 + Spring 4 Dashboard!"));
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("userCount", userService.getAll().size() + " users registered")); 
    }

	@Override
	public IModel<?> getTitle() {
		return Model.of("homePage");

	}
}
