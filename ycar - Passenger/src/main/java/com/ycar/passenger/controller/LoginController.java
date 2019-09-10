package com.ycar.passenger.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.passenger.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping("/members/login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	// 로그인
	@PostMapping
	public Map<String, Object> login(@RequestParam("id") String id, @RequestParam("pw") String pw) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = loginService.login(id, pw);

		return map;
	}

	// 카카오 로그인
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Map<String, Object> kakaoLogin(@PathVariable("id") String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = loginService.kakaoLogin(id);

		return map;
	}

	// 아이디 찾기
	@GetMapping("/findId")
	public int findId(@RequestParam("name") String name, @RequestParam("email") String email) {

		int result = loginService.findId(name, email);

		return result;
	}

	// 비밀번호 찾기
	@GetMapping("/findPw")
	public int findPw(@RequestParam("name") String name, @RequestParam("email") String email) {

		int result = loginService.findPw(name, email);

		return result;
	}
}
