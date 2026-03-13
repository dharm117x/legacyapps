package com.example.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;

public class HelloViewImpl extends Composite implements HelloView {

    interface Binder extends UiBinder<Widget, HelloViewImpl> {}

    private static Binder uiBinder = GWT.create(Binder.class);

    @UiField
    Label messageLabel;

    public HelloViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void setMessage(String msg) {
        messageLabel.setText(msg);
    }
}