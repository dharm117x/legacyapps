package com.example.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserController() {
		System.out.println("IndexController.IndexController()");
	}

	@GetMapping
	public String index() {
		
		return "index";
	}
	
	@GetMapping("/users")
	public String getEmployee() throws SQLException {
		List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from employee");
		list.forEach(map->{
			map.forEach((key, value)->{
				System.out.println(key +"---"+ value);
			});
		});
		return "index";
	}

}
