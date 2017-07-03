package com.scsoft.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scsoft.base.po.User;

@RestController
public class UserController {

	@GetMapping("/user")
	public Object list(User user){
		
		return user;
	}
}
