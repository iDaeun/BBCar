package com.ycar.passenger.dao;

import java.util.Map;

import com.ycar.passenger.domain.PassengerInfo;

public interface PassengerDao {
	public PassengerInfo selectById(String id); // 로그인, 마이페이지
	public PassengerInfo selectByName(String name); // 아이디 찾기
	public int updatePw(Map<String,String>map); // 임시비밀번호로 변경
	public int changeVer(String id); // 임시비밀번호 발송 -> verify = N으로 변경
}
