package com.example.shared.model.user;

import java.io.Serializable;

public class UserTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;

	public UserTO() {
	} // Required for GWT serialization

	public UserTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserTO(Integer id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	// Getters and Setters following JavaBean naming conventions
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
