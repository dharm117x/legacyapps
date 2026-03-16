package com.example.panel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public HeaderPanel(String id) {
		super(id);
		add(new Label("username", "Admin User"));
		add(new Label("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
	}

}
