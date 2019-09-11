package com.ycar.pClient.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 탑승자 or 운전자 로그인에 따라 다른 페이지로 이동
public class ChangePagesInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);

		if (session != null) {

			String str = (String) session.getAttribute("login");
			// JSONParser parser = new JSONParser();
			// JSONObject obj = new JSONObject();
			// obj = (JSONObject) parser.parse(str);
			// int idx = (int) obj.get("idx");

			Map<String, Object> maps = new HashMap<String, Object>();
			maps = (Map<String, Object>) session.getAttribute("login");
			System.out.println("::INTERCEPTOR::" + maps.get("idx"));
			
			// 운전자 or 탑승자로 
			// DB에서 idx검색, 운전자 or 탑승자로 로그인했는지 확인
//			int idx = (int) maps.get("idx");
//			RestTemplate rt = new RestTemplate();
//			rt.getForObject("", Integer.class, 	Integer.toString(idx));

		}

		return super.preHandle(request, response, handler);
	}

}
