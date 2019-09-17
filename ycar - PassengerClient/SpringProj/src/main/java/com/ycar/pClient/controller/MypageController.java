package com.ycar.pClient.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ycar.pClient.domain.LoginInfo;
import com.ycar.pClient.domain.MypageInfo;

@CrossOrigin
@Controller
@RequestMapping("/mypage")
public class MypageController {

	// 마이페이지 지정
	@GetMapping
	public String page() {

		/*
		 * ModelAndView mav = new ModelAndView(); mav.setViewName("mypage");
		 * 
		 * HttpSession session = request.getSession(); LoginInfo info =
		 * (LoginInfo)session.getAttribute("login"); int idx = info.getIdx();
		 * 
		 * RestTemplate rt = new RestTemplate();
		 * 
		 * MypageInfo myInfo =
		 * rt.getForObject("http://localhost:9090/passenger/members/mypage/{idx}",
		 * MypageInfo.class, idx);
		 * 
		 * System.out.println(myInfo);
		 * 
		 * mav.addObject("name",myInfo.getName()); mav.addObject("id",myInfo.getId());
		 * mav.addObject("email",myInfo.getEmail());
		 * mav.addObject("nickname",myInfo.getNickname());
		 */

		return "mypage";
	}

	// 내 정보 업로드
//	@PostMapping
//	public int changeInfo(@RequestParam("id") String id, @RequestParam("pw1") String pw1,
//			@RequestParam("pw2") String pw2) {
//		
//		RestTemplate rt = new RestTemplate();
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("pw1", pw1);
//		map.put("pw2", pw2);
//		
//		int result = rt.postForObject("http://localhost:9090/passenger/members/mypage", map, Integer.class);
//		
//		// result = 1일때, session에 업데이트된 정보로 다시 저장
//		
//		return result;
//	}
}
