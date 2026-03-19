package com.example.client.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;

public class UserEditDialog extends DialogBox {

	private TextBox nameField = new TextBox();
	private TextBox emailField = new TextBox();
	private Button saveButton = new Button("Save");
	private Button cancelButton = new Button("Cancel");

	public UserEditDialog(String title) {
		setText(title); // Set the header title
		setGlassEnabled(true);
		setAnimationEnabled(true);

		// Layout
		VerticalPanel layout = new VerticalPanel();
		layout.setSpacing(10);

		layout.add(new Label("User Name:"));
		layout.add(nameField);

		layout.add(new Label("Email Address:"));
		layout.add(emailField);

		HorizontalPanel buttons = new HorizontalPanel();
		buttons.setSpacing(5);
		buttons.add(saveButton);
		buttons.add(cancelButton);
		layout.add(buttons);

		setWidget(layout);

		// Close on cancel
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}

	// Helper to fill data for "Edit" mode
	public void editUser(String name, String email) {
		nameField.setText(name);
		emailField.setText(email);
	}

	// Expose the save button so the main app can handle the logic
	public Button getSaveButton() {
		return saveButton;
	}

	public String getNameValue() {
		return nameField.getText();
	}

	public String getEmailValue() {
		return emailField.getText();
	}
}
