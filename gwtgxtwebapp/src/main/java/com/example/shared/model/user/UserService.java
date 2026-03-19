package com.example.shared.model.user;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("userService") 
public interface UserService extends RemoteService {

    String saveUser(UserTO user);

    List<UserTO> getUsers();

    List<UserTO> searchUsers(UserTO user);

    UserTO getUserById(Integer id);

    void deleteUser(Integer id);
}