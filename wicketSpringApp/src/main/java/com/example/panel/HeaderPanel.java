package com.example.panel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.example.config.AppSession;

public class HeaderPanel extends Panel {
	private static final long serialVersionUID = 1L;

	public HeaderPanel(String id) {
		super(id);
		AppSession session = (AppSession) getSession();
		if (session.isSignedIn()) {
			add(new Label("username", session.getUsername()));
		} else {
			add(new Label("username", "Guest"));
		}
		add(new Label("currentDate", LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
	}

}
