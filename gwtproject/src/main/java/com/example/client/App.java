package com.example.client;

import com.example.client.presenter.HelloPresenter;
import com.google.gwt.core.client.EntryPoint;

public class App implements EntryPoint {

    public void onModuleLoad() {

        HelloPresenter presenter = new HelloPresenter();
        presenter.go();

    }
}