package com.example.page;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.example.service.UserService;

public class HomePage extends TemplatePage {
    private static final long serialVersionUID = 1L;

    @SpringBean
    private UserService userService;

    public HomePage() {
        add(new Label("message", "Welcome to the Wicket 8 + Spring 4 Dashboard!"));
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("userCount", userService.getCount())); 
    }
}
