package com.example.panel.user;

import java.util.List;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.example.model.User;
import com.example.service.UserService;

public class SearchPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public SearchPanel(String id, IModel<List<User>> listModel, UserService service) {
	    super(id);

	    // 1. Create a Form
	    Form<Void> form = new Form<>("searchForm");
	    add(form);

	    // 2. Add components TO THE FORM, not the panel
	    TextField<String> searchField = new TextField<>("search", Model.of(""));
	    form.add(searchField);

	    form.add(new Button("searchBtn") {
	        private static final long serialVersionUID = 1L;
	        @Override
	        public void onSubmit() {
	            // This will now trigger correctly
	            String keyword = searchField.getModelObject();
	            listModel.setObject(service.search(keyword));
	        }
	    });
	}
}