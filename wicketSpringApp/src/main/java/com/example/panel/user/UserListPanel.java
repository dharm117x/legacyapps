package com.example.panel.user;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.example.model.User;
import com.example.service.UserService;

public class UserListPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public UserListPanel(String id, IModel<List<User>> listModel, IModel<User> userModel, UserService service) {
		super(id);

		ListView<User> listView = new ListView<User>("users", listModel) {
			@Override
			protected void populateItem(ListItem<User> item) {

				User user = item.getModelObject();

				item.add(new Label("name", user.getName()));
				item.add(new Label("email", user.getEmail()));

				// Edit
				item.add(new Link<Void>("edit") {
					@Override
					public void onClick() {
						userModel.setObject(user);
					}
				});

				// Delete
				item.add(new Link<Void>("delete") {
					@Override
					public void onClick() {
						service.delete(user);
						listModel.setObject(service.getAll());
					}
				});
			}
		};

		add(listView);
	}
}