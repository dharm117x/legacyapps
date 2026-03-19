package com.example.client.presenter.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.client.view.user.UserViewImpl;
import com.example.shared.model.user.UserTO;

public class UserPresenter {

    private UserViewImpl view;
    private List<UserTO> masterData = new ArrayList<>();

    public UserPresenter(UserViewImpl view) {
        this.view = view;
        bind();
        loadData();
    }

    private void bind() {
		view.getSearchForm().getSearchButton().addSelectHandler(event -> {
			String name = view.getSearchForm().getName();
			String email = view.getSearchForm().getEmail();

			List<UserTO> filtered = masterData.stream()
					.filter(u -> (name == null || u.getName().toLowerCase().contains(name.toLowerCase()))
							&& (email == null || u.getEmail().toLowerCase().contains(email.toLowerCase())))
					.collect(Collectors.toList());

			view.getGrid().setData(filtered);
		});
     
		view.getSearchForm().getResetButton().addSelectHandler(event -> {
			view.getSearchForm().clear();
			view.getGrid().setData(masterData);
		});

        view.getToolBar().getAddButton().addSelectHandler(event -> {
            UserTO u = new UserTO(masterData.size() + 1,
                "UserTO " + masterData.size(),
                "UserTO@mail.com");

            masterData.add(u);
            view.getGrid().setData(masterData);
        });

        view.getToolBar().getDeleteButton().addSelectHandler(event -> {
            UserTO selected = view.getGrid().getSelected();
            if (selected != null) {
                masterData.remove(selected);
                view.getGrid().setData(masterData);
            }
        });
    }

    private void loadData() {
        masterData.add(new UserTO(1, "John", "john@mail.com"));
        masterData.add(new UserTO(2, "Jane", "jane@mail.com"));

        view.getGrid().setData(masterData);
    }
}