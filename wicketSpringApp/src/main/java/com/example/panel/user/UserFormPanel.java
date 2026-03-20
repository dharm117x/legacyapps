package com.example.panel.user;

import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import com.example.model.User;
import com.example.service.UserService;

public class UserFormPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public UserFormPanel(String id, IModel<User> userModel, UserService service, IModel<List<User>> listModel) {
		super(id, userModel);

		CompoundPropertyModel<User> compoundModel = new CompoundPropertyModel<>(userModel);
		Form<User> form = new Form<>("form", compoundModel);

		form.add(new TextField<String>("name").setRequired(true));
		form.add(new TextField<String>("email").setRequired(true));

		form.add(new Button("save") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				service.save(userModel.getObject());
				listModel.setObject(service.getAll());

				// ✅ Reset the model with a fresh User object
				userModel.setObject(new User());
			}
		});

		add(form);
	}

}