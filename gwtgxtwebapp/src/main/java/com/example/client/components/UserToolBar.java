package com.example.client.components;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class UserToolBar {

	private ToolBar toolBar;
	private TextButton addBtn;
	private TextButton editBtn;
	private TextButton deleteBtn;

	public UserToolBar() {

		addBtn = new TextButton("Add");
		editBtn = new TextButton("Edit");
		deleteBtn = new TextButton("Delete");

		toolBar = new ToolBar();
		toolBar.add(addBtn);
		toolBar.add(editBtn);
		toolBar.add(deleteBtn);
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public TextButton getAddButton() {
		return addBtn;
	}
	
	public TextButton getEditButton() {
		return editBtn;
	}

	public TextButton getDeleteButton() {
		return deleteBtn;
	}
}