package com.example.service;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	UserService() {
		System.out.println("UserService.UserService()");
	}
	
	public IModel<?> getCount() {
		return null;
	}

}
