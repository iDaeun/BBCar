package com.ycar.passenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.passenger.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/members/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public int login(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw
			) {
		
		int msg = loginService.login(id,pw);
		
		return msg;
	}
	
}
