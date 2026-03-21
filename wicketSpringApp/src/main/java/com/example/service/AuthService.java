package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	public boolean authenticate(String username, String password) {
		if ("a".equals(username) && "a".equals(password)) {
			return true;
		}
		return false;
	}

}
