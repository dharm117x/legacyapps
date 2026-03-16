package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

	AuthService() {
		System.out.println("AuthService.AuthService()");
	}
	
	public boolean authenticate(String username, String password) {
		if ("admin".equals(username) && "admin".equals(password)) {
			return true;
		}
		return false;
	}

}
