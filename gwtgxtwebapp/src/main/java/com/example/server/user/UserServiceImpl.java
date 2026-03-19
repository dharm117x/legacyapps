package com.example.server.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.shared.model.user.UserService;
import com.example.shared.model.user.UserTO;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet  implements UserService {
	private static final long serialVersionUID = 1L;
	
	private List<UserTO> users = new ArrayList<>();
	 
	public UserServiceImpl() {
		users.add(new UserTO(1, "John", "john@mail.com"));
		users.add(new UserTO(2, "Jane", "jane@mail.com"));
	}
     
	@Override
	public String saveUser(UserTO user) {
		users.add(user);
		return "User " + user.getName() + " with email " + user.getEmail() + " saved successfully!";
	}

	@Override
    public List<UserTO> getUsers() {
		return users;
	}


	@Override
	public List<UserTO> searchUsers(UserTO user) {
		List<UserTO> filters = users.stream().filter(u -> (user.getName() == null
				|| u.getName().toLowerCase().contains(user.getName().toLowerCase()))
				&& (user.getEmail() == null || u.getEmail().toLowerCase().contains(user.getEmail().toLowerCase())))
				.collect(Collectors.toList());
		return filters;
	}

	@Override
	public UserTO getUserById(Integer id) {
		return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public void deleteUser(Integer id) {
		users.removeIf(user -> user.getId().equals(id));
	}

}
