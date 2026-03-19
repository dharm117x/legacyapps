package com.example.client.components;

import java.util.ArrayList;
import java.util.List;

import com.example.client.model.user.UserProps;
import com.example.shared.model.user.UserTO;
import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class UserGrid {

    private Grid<UserTO> grid;
    private ListStore<UserTO> store;

    public UserGrid() {
        UserProps props = GWT.create(UserProps.class);

		// 1. Setup the Store
		store = new ListStore<>(props.key());

		// 2. Setup Columns
		ColumnConfig<UserTO, String> nameCol = new ColumnConfig<>(props.name(), 150, "User Name");
		ColumnConfig<UserTO, String> emailCol = new ColumnConfig<>(props.email(), 200, "Email Address");

		List<ColumnConfig<UserTO, ?>> columns = new ArrayList<>();
		columns.add(nameCol);
		columns.add(emailCol);

		ColumnModel<UserTO> cm = new ColumnModel<>(columns);

		// 3. Initialize Grid
		grid = new Grid<>(store, cm);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);

    }

    public Grid<UserTO> getGrid() {
        return grid;
    }

    public void setData(List<UserTO> data) {
        store.clear();
        store.addAll(data);
    }

    public UserTO getSelected() {
        return grid.getSelectionModel().getSelectedItem();
    }
}