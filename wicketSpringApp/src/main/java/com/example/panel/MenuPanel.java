package com.example.panel;

import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.example.page.HomePage;
import com.example.page.LoginPage;

public class MenuPanel extends Panel {
    private static final long serialVersionUID = 1L;

    public MenuPanel(String id) {
        super(id);

        // Dummy Home Link using Java 8 Lambda
        add(new StatelessLink<Void>("homeLink") {
            @Override
            public void onClick() {
                setResponsePage(HomePage.class);
            }
        });

        // Dummy Login Link
        add(new StatelessLink<Void>("loginLink") {
            @Override
            public void onClick() {
                setResponsePage(LoginPage.class);
            }
        });
    }
}
