package com.example.client.view.user;

import java.util.List;

import com.example.client.components.UserGrid;
import com.example.client.components.UserSearchForm;
import com.example.client.components.UserToolBar;
import com.example.shared.model.user.UserTO;
import com.google.gwt.user.client.ui.Widget;

public interface UserView {

    Widget asWidget();

    UserGrid getGrid();
    UserToolBar getToolBar();
    UserSearchForm getSearchForm();

    void setData(List<UserTO> data);
}