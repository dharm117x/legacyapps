package com.example.page.user;

import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.example.config.MenuItem;
import com.example.model.User;
import com.example.page.HomePage;
import com.example.page.TemplatePage;
import com.example.panel.user.SearchPanel;
import com.example.panel.user.UserFormPanel;
import com.example.panel.user.UserListPanel;
import com.example.service.UserService;

public class UserPage extends TemplatePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
    private UserService service;

    public UserPage() {
    	crumbs.add(new MenuItem("Home", HomePage.class));
    	crumbs.add(new MenuItem("User", UserPage.class));
    	
        IModel<User> userModel = new Model<>(new User());
        IModel<List<User>> listModel = new ListModel<>(service.getAll());

        // Search Panel
        add(new SearchPanel("searchPanel", listModel, service));

        // Form Panel
        add(new UserFormPanel("formPanel", userModel, service, listModel));

        // List Panel
        add(new UserListPanel("listPanel", listModel, userModel, service));
    }

	@Override
	public IModel<?> getTitle() {
		return Model.of("userPage");

	}
}