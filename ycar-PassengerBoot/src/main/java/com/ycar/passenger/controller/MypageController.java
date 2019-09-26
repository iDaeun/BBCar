package com.ycar.passenger.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.passenger.dao.PassengerDaoImpl;
import com.ycar.passenger.entity.DCarpoolEntity;
import com.ycar.passenger.entity.PRouteEntity;
import com.ycar.passenger.entity.PassengerEntity;
import com.ycar.passenger.repository.CarPoolRepository;
import com.ycar.passenger.repository.MemoRepository;

@RestController
@Controller
@CrossOrigin
public class MypageController {

	// -- 탑승자 메모 기능 --

	@Autowired
	private CarPoolRepository cpRepo;
	
		// 등록된 카풀 리스트 출력 : 예약이 아직 되지 않은 카풀 등록 리스트
	@RequestMapping("/cpList")
	public List<DCarpoolEntity> cpList() {
		
		System.out.println("탑승자 메모 01");

		List<DCarpoolEntity> list = cpRepo.list();
		//List<DCarpoolEntity> list = cpRepo.findAll();
		
		for (DCarpoolEntity dCarpoolEntity : list) {
			System.out.println("탑승자 메모 02-1" + dCarpoolEntity);
		}
		
		return list;

	}
	
	@Autowired
	private MemoRepository mmRepo;
	
		// 카풀 선택하여 메모 작성
	@PostMapping("/writeMemo/{pIdx}/{dIdx}")
	public String writeMemo(@PathVariable("pidx") long pIdx, @PathVariable("dIdx") long dIdx, String memo) {
		
		int result = mmRepo.writeMemo(pIdx,dIdx,memo);
		
		return result>0?"success":"fail";		
	}
	
		// 작성한 메모에 대한 카풀이 예약 되었는지 확인 : Y 예약됨 / B 예약 대기
	
		// 메모 수정
		
		// 메모 삭제

	// --- 탑승자 개인 정보 ---

	@PersistenceContext
	EntityManager entityManager;

	private PassengerDaoImpl dao;

	@RequestMapping("/findEnv/{idx}")
	public PassengerEntity findEnvByIdx(@PathVariable("idx") long idx) {

		this.dao = new PassengerDaoImpl(entityManager);

		PassengerEntity entity = dao.findEnvByIdx(idx);
		System.out.println(entity);

		return entity;
	}

	@RequestMapping("/findRoute/{idx}")
	public List<PRouteEntity> findRouteByIdx(@PathVariable("idx") int idx) {

		System.out.println(idx);

		this.dao = new PassengerDaoImpl(entityManager);

		List<PRouteEntity> entity = dao.findRouteByIdx(idx);
		System.out.println(entity);

		return entity;
	}

}
