package com.ycar.pClient.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ChangePagesInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession(false);

		if (session != null) {

			try {
				String str = (String) session.getAttribute("login");
				JSONParser parser = new JSONParser();
				JSONObject obj = new JSONObject();
				obj = (JSONObject) parser.parse(str);
				int idx = (int) obj.get("idx");

				System.out.println(idx);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

		return super.preHandle(request, response, handler);
	}

}
