package com.example.shared.model.user;

import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

public interface UserServiceAsync {

    void saveUser(UserTO user, AsyncCallback<String> callback);

    void getUsers(AsyncCallback<List<UserTO>> callback);

    void searchUsers(UserTO user, AsyncCallback<List<UserTO>> callback);

    void getUserById(Integer id, AsyncCallback<UserTO> callback);

    void deleteUser(Integer id, AsyncCallback<Void> callback);
}