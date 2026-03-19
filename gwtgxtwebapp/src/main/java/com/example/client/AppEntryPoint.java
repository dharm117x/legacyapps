package com.example.client;

import com.example.client.presenter.HelloPresenter;
import com.example.client.presenter.user.UserPresenter;
import com.example.client.view.HelloView;
import com.example.client.view.HelloViewImpl;
import com.example.client.view.user.UserViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class AppEntryPoint implements EntryPoint {

    public void onModuleLoad() {
    	HelloView helloView = new HelloViewImpl();
        new HelloPresenter(helloView);
        RootPanel.get("helloContainer").add(helloView.asWidget());
        
        UserViewImpl view = new UserViewImpl();
        new UserPresenter(view);

        RootPanel.get("userContainer").add(view.asWidget());

    }
}