package com.example.client.presenter;

import com.google.gwt.core.client.GWT;
import com.example.client.view.HelloView;

public class HelloPresenter {

	private HelloView view;

	public HelloPresenter(HelloView view) {
		this.view = view;
		go();
	}

	public void go() {
		GWT.log("HelloPresenter is going");
		view.setMessage("Hello from MVP Presenter");
	}
}