package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.model.User;

@Service
public class UserService {

    private static List<User> users = new ArrayList<>();
    private static Long counter = 1L;

    public List<User> getAll() {
        return users;
    }
    
    public List<User> search(String keyword) {
        return users.stream()
                .filter(u -> u.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(counter++);
            users.add(user);
		} else {
			User existingUser = users.stream().filter(u -> u.getId().equals(user.getId())).findFirst().orElse(null);
			if (existingUser != null) {
				existingUser.setName(user.getName());
				existingUser.setEmail(user.getEmail());
			}
		}
    }

    public void delete(User user) {
        users.remove(user);
    }

}