package com.ycar.passenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.passenger.domain.MypageEdit;
import com.ycar.passenger.service.MypageService;

@RestController
@CrossOrigin
@RequestMapping("/members/mypage")
public class MypageController {

	@Autowired
	private MypageService mypageService;

	@PutMapping
	public int changeInfo(@RequestBody MypageEdit edit) {

		int result = mypageService.changeInfo(edit.getId(),edit.getPw1(), edit.getPw2());
		
		return result;
	}
}
