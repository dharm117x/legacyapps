package com.example.client.components;

import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class UserToolBar {

	private ToolBar toolBar;
	private TextButton addBtn;
	private TextButton deleteBtn;

	public UserToolBar() {
		addBtn = new TextButton("Add");
		deleteBtn = new TextButton("Delete");

		toolBar = new ToolBar();
		toolBar.add(addBtn);
		toolBar.add(deleteBtn);
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public TextButton getAddButton() {
		return addBtn;
	}

	public TextButton getDeleteButton() {
		return deleteBtn;
	}
}