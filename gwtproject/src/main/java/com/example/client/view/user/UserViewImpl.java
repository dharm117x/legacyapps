package com.example.client.view.user;

import java.util.List;

import com.example.client.components.UserGrid;
import com.example.client.components.UserSearchForm;
import com.example.client.components.UserToolBar;
import com.example.shared.model.user.UserTO;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class UserViewImpl implements UserView {

    private VerticalLayoutContainer container;

    private UserGrid userGrid;
    private UserToolBar toolBar;
    private UserSearchForm searchForm;

    public UserViewImpl() {

        userGrid = new UserGrid();
        toolBar = new UserToolBar();
        searchForm = new UserSearchForm();

        container = new VerticalLayoutContainer();

        container.add(searchForm.asWidget(), new VerticalLayoutData(1, -1));
        container.add(toolBar.getToolBar(), new VerticalLayoutData(1, -1));
        container.add(userGrid.getGrid(), new VerticalLayoutData(1, 1));
    }

    @Override
    public Widget asWidget() {
        return container;
    }

    public UserGrid getGrid() {
        return userGrid;
    }

    public UserToolBar getToolBar() {
        return toolBar;
    }

    public UserSearchForm getSearchForm() {
        return searchForm;
    }

	@Override
	public void setData(List<UserTO> data) {
        userGrid.setData(data);		
	}

}
