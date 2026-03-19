package com.example.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.example.client.view.HelloView;
import com.example.client.view.HelloViewImpl;

public class HelloPresenter {

	private HelloView view;

	public HelloPresenter() {
		view = new HelloViewImpl();
	}

	public void go() {
		GWT.log("HelloPresenter is going");
		com.google.gwt.core.shared.GWT.log("Sahrresd HelloPresenter is going");
		
		view.setMessage("Hello from MVP Presenter");
		RootPanel.get().add(view.asWidget());

	}
}