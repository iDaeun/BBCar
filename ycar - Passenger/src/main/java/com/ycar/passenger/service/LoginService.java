package com.ycar.passenger.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ycar.passenger.dao.PassengerDao;
import com.ycar.passenger.domain.PassengerInfo;

@Service("loginService")
public class LoginService {
	
	@Autowired
	private SqlSessionTemplate template;
	private PassengerDao dao;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public int login(String id, String pw) {
		
		dao = template.getMapper(PassengerDao.class);
		
		int msg = 0;
		
		PassengerInfo passengerInfo = dao.selectById(id);
		
		if(passengerInfo==null) {
			msg = 1; // 존재하지 않는 회원
		} else if(passengerInfo.getVerify() == 'Y' && passengerInfo.pwMatch(pw)) {
			msg = 2; // 인증처리된 회원, 정상 로그인
		} else if(passengerInfo.getVerify() == 'N') {
			msg = 3; // 인증 미완료
		} else if(!passengerInfo.pwMatch(pw)) {
			msg = 4; // 비밀번호 불일치
		}
					
//			if(passengerInfo.getVerify() == 'Y' && passengerInfo!=null && passengerInfo.pwMatch(pw)) {
//			//if(verify == 'Y' && passengerInfo!=null && encoder.matches(pw, passengerInfo.getPw())) {
//				msg = 1; // 인증처리된 회원, 정상 로그인
//			} else if(passengerInfo.getVerify() == 'N') {
//				msg = 2; // 인증 미완료
//			} else if(passengerInfo == null) {
//				msg = 3; // 존재하지 않는 회원
//			} else if(!encoder.matches(pw, passengerInfo.getPw())) {
//				msg = 4; // 비밀번호 불일치
//			}
					
		return msg;
	}

}