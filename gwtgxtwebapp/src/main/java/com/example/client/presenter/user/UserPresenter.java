package com.example.client.presenter.user;

import java.util.List;

import com.example.client.components.UserEditDialog;
import com.example.client.view.user.UserViewImpl;
import com.example.shared.model.user.UserService;
import com.example.shared.model.user.UserServiceAsync;
import com.example.shared.model.user.UserTO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class UserPresenter {
	private UserServiceAsync service;
	private UserViewImpl view;

	public UserPresenter(UserViewImpl view) {
		this.service = GWT.create(UserService.class);
		this.view = view;
		bind();
		loadUsers();
	}

	public void loadUsers() {
		service.getUsers(new AsyncCallback<List<UserTO>>() {
			@Override
			public void onSuccess(List<UserTO> result) {
				view.getGrid().setData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Load Failed: " + caught.getMessage());
			}
		});
	}

	private void bind() {
		// SEARCH: Now calls the service with filters
		view.getSearchForm().getSearchButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				UserTO filter = new UserTO();
				filter.setName(view.getSearchForm().getName());
				filter.setEmail(view.getSearchForm().getEmail());

				service.searchUsers(filter, new AsyncCallback<List<UserTO>>() {
					@Override
					public void onSuccess(List<UserTO> result) {
						view.getGrid().setData(result);
					}

					@Override
					public void onFailure(Throwable caught) {
						/* handle error */ }
				});
			}
		});

		// RESET: Simply reloads all data from server
		view.getSearchForm().getResetButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				view.getSearchForm().clear();
				loadUsers();
			}
		});

		// ADD: Saves to server, then reloads
		view.getToolBar().getAddButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final UserEditDialog dialog = new UserEditDialog("Add New User");
				dialog.center();
				dialog.getSaveButton().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						UserTO newUser = new UserTO(0, dialog.getNameValue(), dialog.getEmailValue());
						service.saveUser(newUser, new AsyncCallback<String>() {
							@Override
							public void onSuccess(String result) {
								loadUsers(); // Refresh grid from DB
								dialog.hide();
								Window.alert("User added with ID: " + result);
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Save Failed");
							}
						});
					}

				});
			}
		});
		
		// ADD: Saves to server, then reloads
		view.getToolBar().getEditButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final UserTO selected = view.getGrid().getSelected();
				final UserEditDialog dialog = new UserEditDialog("Edit User");
				dialog.editUser(selected.getName(), selected.getEmail());
				dialog.center();
				dialog.getSaveButton().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						final UserTO updatedUser = new UserTO(selected.getId(), dialog.getNameValue(), dialog.getEmailValue());
						service.saveUser(updatedUser, new AsyncCallback<String>() {
							@Override
							public void onSuccess(String result) {
								loadUsers(); // Refresh grid from DB
								dialog.hide();
							}

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Save Failed");
							}
						});
					}

				});
			}
		});

		// DELETE: Removes from server, then reloads
		view.getToolBar().getDeleteButton().addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				final UserTO selected = view.getGrid().getSelected();
				if (selected != null) {
					service.deleteUser(selected.getId(), new AsyncCallback<Void>() {
						@Override
						public void onSuccess(Void result) {
							loadUsers(); // Refresh grid from DB
						}

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Delete Failed");
						}
					});
				}
			}
		});
	}
}
