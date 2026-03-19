package com.example.client;

import com.example.client.presenter.HelloPresenter;
import com.example.client.presenter.user.UserPresenter;
import com.example.client.view.user.UserViewImpl;
import com.google.gwt.core.client.EntryPoint;

public class AppEntryPoint implements EntryPoint {

    public void onModuleLoad() {

        HelloPresenter presenter = new HelloPresenter();
        presenter.go();
        UserViewImpl view = new UserViewImpl();
        new UserPresenter(view);

    }
}