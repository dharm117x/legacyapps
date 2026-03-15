package com.example.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.dto.UserTo;

@Controller
public class UserController {
	
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserController() {
		LOG.info("UserController instance created");
	}

	@GetMapping
	public String index() {
		LOG.info("UserController.index() called");
		return "index";
	}
	
	@GetMapping("/users")
	public String getUsers(ModelMap map) throws SQLException {
		List<UserTo> list = jdbcTemplate.query("select id, name, email, created_at as createdAt from AppUsers",  new BeanPropertyRowMapper<>(UserTo.class));
		LOG.info("Fetched {} users from database", list.size());
		map.addAttribute("users", list);
		return "users";
	}

}
