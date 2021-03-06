package com.ycar.passenger.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ycar.passenger.domain.LoginInfo;

@CrossOrigin
@Controller
@RequestMapping("/login")
public class LoginController {

	// 로그인 페이지 로딩
	@GetMapping
	public String page() {
		return "login";
	}

	// 로그인 기능
	@PostMapping
	@ResponseBody
	public int login(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpServletRequest request) {
		
		System.out.println("id 왜 안나와"+ id);

		RestTemplate rt = new RestTemplate();

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);

		Map<String, Object> maps = new HashMap<String, Object>();

		maps = rt.postForObject("http://13.125.252.85:8080/server/members/login", map, Map.class);
		//maps = rt.postForObject("http://localhost:8080/server/members/login", map, Map.class);
		
		// VIEW에 message전달
		int msg = (Integer) maps.get("msg");

		if (msg == 2 || msg == 4) {
			// 임시비밀번호 발급 회원 & 성공적으로 로그인한 회원 --> session에 저장
			HttpSession session = request.getSession(false);
			LinkedHashMap<String, Object> hashM = new LinkedHashMap<String, Object>();
			hashM = (LinkedHashMap<String, Object>) maps.get("login");

			int idx = (Integer) hashM.get("idx");
			String nickname = (String) hashM.get("nickname");
			String email = (String) hashM.get("email");
			String name = (String) hashM.get("name");
			char type = hashM.get("type").toString().toCharArray()[0];
			System.out.println(type);

			LoginInfo info = new LoginInfo(idx, id, nickname, email, name, type);

			session.setAttribute("login", info);
			System.out.println("(일반)로그인 후 저장된 session:" + session.getAttribute("login"));
		}

		return msg;
	}

	// 카카오 로그인
	@RequestMapping(value = "/kakao/{id}", method = RequestMethod.GET)
	@ResponseBody
	public int kakaoLogin(@PathVariable("id") String id, HttpServletRequest request) {

		RestTemplate rt = new RestTemplate();

		Map<String, Object> maps = new HashMap<String, Object>();

		maps = rt.getForObject("http://13.125.252.85:8080/server/members/login/{id}", Map.class, id);

		// VIEW에 message전달
		int msg = (Integer) maps.get("msg");

		if (msg == 2) {
			// 로그인 성공 시 session에 저장
			HttpSession session = request.getSession(false);
			LinkedHashMap<String, Object> hashM = new LinkedHashMap<String, Object>();
			hashM = (LinkedHashMap<String, Object>) maps.get("login");

			int idx = (Integer) hashM.get("idx");
			String nickname = (String) hashM.get("nickname");
			String email = (String) hashM.get("email");
			String name = (String) hashM.get("name");
			char type = hashM.get("type").toString().toCharArray()[0];
			System.out.println(type);

			LoginInfo info = new LoginInfo(idx, id, nickname, email, name, type);

			session.setAttribute("login", info);
			System.out.println("(kakao)로그인 후 저장된 session:" + session.getAttribute("login"));
		}

		return msg;
	}

	// 아이디 찾기
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	@ResponseBody
	public int findId(@RequestParam("name") String name, @RequestParam("email") String email) {

		RestTemplate rt = new RestTemplate();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", name);
		map.add("email", email);

		HttpHeaders headers = new HttpHeaders();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		int result = 0;

		result = rt
				.exchange("http://13.125.252.85:8080/server/members/login/findId", HttpMethod.POST, entity, Integer.class)
				.getBody();
		// result =
		// rt.postForObject("http://localhost:9090/passenger/members/login/findId", map,
		// Integer.class);
		return result;
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	@ResponseBody
	public int findPw(@RequestParam("name") String name, @RequestParam("email") String email) {

		RestTemplate rt = new RestTemplate();

		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);

		int result = 0;

		result = rt.postForObject("http://13.125.252.85:8080/server/members/login/findPw", map, Integer.class);

		return result;
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session.getAttribute("login") != null) {
			session.invalidate();
			return "로그아웃 success";
		}

		return "로그아웃 fail";

	}
}
