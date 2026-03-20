package com.example.panel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.example.config.AppSession;
import com.example.config.MenuItem;
import com.example.page.HomePage;
import com.example.page.LoginPage;
import com.example.page.user.UserPage;
import com.example.page.user.UserRegistrationPage;

public class HeaderPanel extends Panel {
	private static final long serialVersionUID = 1L;
    List<MenuItem> items  = new ArrayList<MenuItem>();
    
	public HeaderPanel(String id) {
		super(id);
		AppSession session = (AppSession) getSession();
		WebMarkupContainer menu = new WebMarkupContainer("menu");
        menu.setOutputMarkupId(true);
        add(menu);
        
		// Menu items list
		if (!session.isSignedIn()) {
			items.add(new MenuItem("Login", LoginPage.class));
			items.add(new MenuItem("Register", UserRegistrationPage.class));
		}

		if (session.isSignedIn()) {
			items.add(new MenuItem("Home", HomePage.class));
			items.add(new MenuItem("User", UserPage.class));
		}
        
		menu.add(new ListView<MenuItem>("menuItems", items) {
            @Override
            protected void populateItem(ListItem<MenuItem> item) {
                MenuItem menuItem = item.getModelObject();
                
                if (menuItem.getPageClass().equals(LoginPage.class) && session.isSignedIn()) {
                    item.setVisible(false);
                }
                // 1. Create the link and label
                BookmarkablePageLink<Void> link = new BookmarkablePageLink<>("link", menuItem.getPageClass());
                link.add(new Label("label", menuItem.getLabel()));
                item.add(link);

                // 2. Handle CSS Classes
                // Always add nav-item for Bootstrap spacing
                item.add(AttributeModifier.append("class", "nav-item"));

                // Highlight active page
                if (getPage().getClass().equals(menuItem.getPageClass())) {
                    item.add(AttributeModifier.append("class", "active font-weight-bold"));
                }
            }
        });
    	if (session.isSignedIn()) {
    		menu.add(new Label("username", "User: "+ session.getUsername()));
		} else {
			menu.add(new Label("username", "User: Guest"));
		}
    	menu.add(new Label("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    	
        menu.add(new Link<Void>("logout") {
            @Override
            public void onClick() {
                getSession().invalidate(); // Clear session
                setResponsePage(LoginPage.class); // Redirect
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();
                // Only show logout if user is signed in
                setVisible(((com.example.config.AppSession) getSession()).isSignedIn());
            }
        }.add(AttributeModifier.append("class", "nav-link text-danger")));
	}
}
