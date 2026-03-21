package com.example.page.user;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import com.example.config.AppSession;
import com.example.model.User;
import com.example.page.HomePage;
import com.example.page.LoginPage;
import com.example.page.TemplatePage;
import com.example.service.UserService;

@MountPath("/register")
public class UserRegistrationPage extends TemplatePage {
	private static final long serialVersionUID = 1L;

	@SpringBean
    private UserService service;
	
	public UserRegistrationPage() {
		FeedbackPanel feedback = new FeedbackPanel("feedback") {
		    @Override
		    protected void onConfigure() {
		        super.onConfigure();
		        setVisible(anyMessage());
		    }
		};
		
        add(feedback);

        Form<User> form = new Form<>("registerForm", new CompoundPropertyModel<>(new User()));
        
        form.add(new TextField<String>("name").setRequired(true));
        form.add(new TextField<String>("email").setRequired(true));
        form.add(new PasswordTextField("password").setRequired(true));

     // Change this from 'new Form' to 'new Button'
        form.add(new Button("submit") { 
            @Override
            public void onSubmit() {
            	AppSession session = (AppSession) getSession();
                User newUser = (User) getForm().getModelObject();
                session.setUsername(newUser.getName());
                service.save(newUser);
                setResponsePage(HomePage.class);
            }
        });
        
        form.add(new BookmarkablePageLink<>("loginLink", LoginPage.class));
        add(form);
      
    }

	@Override
	public IModel<?> getTitle() {
		return Model.of("registerPage");

	}
}
