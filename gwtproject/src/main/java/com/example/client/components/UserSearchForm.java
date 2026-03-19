package com.example.client.components;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class UserSearchForm {

	private HorizontalLayoutContainer layout;

	private TextField nameField;
	private TextField emailField;

	private TextButton searchBtn;
	private TextButton resetBtn;

	public UserSearchForm() {

		nameField = new TextField();
		emailField = new TextField();

		searchBtn = new TextButton("Search");
		resetBtn = new TextButton("Reset");

		layout = new HorizontalLayoutContainer();

		layout.add(new FieldLabel(nameField, "Name"), new HorizontalLayoutData(0.3, -1));

		layout.add(new FieldLabel(emailField, "Email"), new HorizontalLayoutData(0.3, -1));

		layout.add(searchBtn, new HorizontalLayoutData(-1, -1));
		layout.add(resetBtn, new HorizontalLayoutData(-1, -1));
	}

	public Widget asWidget() {
		return layout;
	}

	public String getName() {
		return nameField.getValue();
	}

	public String getEmail() {
		return emailField.getValue();
	}

	public void clear() {
		nameField.clear();
		emailField.clear();
	}

	public TextButton getSearchButton() {
		return searchBtn;
	}

	public TextButton getResetButton() {
		return resetBtn;
	}
}