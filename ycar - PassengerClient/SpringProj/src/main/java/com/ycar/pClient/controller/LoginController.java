package com.ycar.pClient.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ycar.pClient.domain.LoginInfo;

@CrossOrigin
@Controller
@RequestMapping("/login")
public class LoginController {

	// 로그인 페이지 지정
	@GetMapping
	public String page() {
		return "login";
	}

	// 로그인 기능
	@RequestMapping(value = "/{userType}", method = RequestMethod.POST)
	@ResponseBody
	public int login(@RequestParam("id") String id, @RequestParam("pw") String pw,
			@PathVariable("userType") int userType, HttpServletRequest request) {

		RestTemplate rt = new RestTemplate();

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);

		// userType에 따라 탑승자 or 운전자 분기처리 (REST SERVER와 통신)
		// 1 = 탑승자 모드 , 2 = 운전자 모드
		Map<String, Object> maps = new HashMap<String, Object>();

		if (userType == 1) {
			maps = rt.postForObject("http://localhost:9090/passenger/members/login", map, Map.class);
		}
		if (userType == 2) {
			// 운전자 SERVER로 전달하기!!
		}
		

		// 로그인 성공 시 session에 저장
		HttpSession session = request.getSession(false);
		LinkedHashMap<String, Object> hashM = new LinkedHashMap<String, Object>();
		hashM = (LinkedHashMap<String, Object>) maps.get("login");
		
		int idx = (int)hashM.get("idx");
		String nickname = (String)hashM.get("nickname");
		String email = (String)hashM.get("email");
		String name = (String)hashM.get("name");
		LoginInfo info = new LoginInfo(idx,id,nickname,email,name); 
		
		session.setAttribute("login", info);
	
		// VIEW에 message전달
		int msg = (int) maps.get("msg");
		return msg;
	}

	// 카카오 로그인
	@RequestMapping(value = "/kakao/{id}/{userType}", method = RequestMethod.POST)
	@ResponseBody
	public String kakaoLogin(@PathVariable("id") String id, @PathVariable("userType") int userType,
			HttpServletRequest request) {

		RestTemplate rt = new RestTemplate();

		// userType에 따라 탑승자 or 운전자 분기처리 (REST SERVER와 통신)
		// 1 = 탑승자 모드 , 2 = 운전자 모드
		Map<String, Object> maps = new HashMap<String, Object>();

		if (userType == 1) {
			maps = rt.getForObject("http://localhost:9090/passenger/members/login/{id}", Map.class, id);
		}
		if (userType == 2) {
			// 운전자 SERVER로 전달하기!!
		}
		
		// 로그인 성공 시 session에 저장
		HttpSession session = request.getSession(false);
		LinkedHashMap<String, Object> hashM = new LinkedHashMap<String, Object>();
		hashM = (LinkedHashMap<String, Object>) maps.get("login");
				
		int idx = (int)hashM.get("idx");
		String nickname = (String)hashM.get("nickname");
		String email = (String)hashM.get("email");
		String name = (String)hashM.get("name");
		LoginInfo info = new LoginInfo(idx,id,nickname,email,name); 
				
		session.setAttribute("login", info);

		return session.getAttribute("login") != null ? "success" : "fail";
	}

	// 아이디 찾기
	@RequestMapping(value = "/findId/{userType}", method = RequestMethod.GET)
	@ResponseBody
	public int findId(@PathVariable("userType") int userType, @RequestParam("name") String name,
			@RequestParam("email") String email) {
				
		RestTemplate rt = new RestTemplate();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email",email);
		
		int result = 0;
		
		if(userType == 1) {
			result = rt.postForObject("http://localhost:9090/passenger/members/login/findId", map, Integer.class);
			System.out.println(result);
		}
		if (userType == 2) {
			// 운전자 SERVER로 전달하기!!
		}
		
		return result;
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/findPw/{userType}", method = RequestMethod.GET)
	@ResponseBody
	public int findPw(@PathVariable("userType") int userType, @RequestParam("name") String name,
			@RequestParam("email") String email) {
				
		RestTemplate rt = new RestTemplate();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email",email);
		
		int result = 0;
		
		if(userType == 1) {
			result = rt.postForObject("http://localhost:9090/passenger/members/login/findPw", map, Integer.class);
			System.out.println(result);
		}
		if (userType == 2) {
			// 운전자 SERVER로 전달하기!!
		}
		
		return result;
	}
}
