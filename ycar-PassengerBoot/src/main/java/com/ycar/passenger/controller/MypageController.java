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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ycar.passenger.dao.PassengerDaoImpl;
import com.ycar.passenger.domain.DChattingDomain;
import com.ycar.passenger.domain.MemoDomain;
import com.ycar.passenger.domain.PChattingDomain;
import com.ycar.passenger.entity.DCarpoolEntity;
import com.ycar.passenger.entity.MemoEntity;
import com.ycar.passenger.entity.PRouteEntity;
import com.ycar.passenger.entity.PassengerEntity;
import com.ycar.passenger.entity.RsvEntity;
import com.ycar.passenger.repository.CarPoolRepository;
import com.ycar.passenger.repository.MemoRepository;
import com.ycar.passenger.repository.RsvRepository;
import com.ycar.passenger.service.ChattingService;
import com.ycar.passenger.service.MemoService;

@RestController
@Controller
@CrossOrigin
public class MypageController {

	// -- 채팅 기능 --

	@Autowired
	private ChattingService chattingService;

	// [채팅] 탑승자가 예약한 카풀 리스트 출력
	@RequestMapping("/PrsvList/{idx}")
	public List<PChattingDomain> PrsvList(@PathVariable("idx") int p_idx) {

		System.out.println("예약 리스트01");

		List<PChattingDomain> list = chattingService.PrsvList(p_idx);

		for (PChattingDomain chattingDomain : list) {
			System.out.println(chattingDomain);
		}

		return list;
	}

	// [채팅] 드라이버가 등록한 카풀을 예약한 리스트 출력
	@RequestMapping("/DrsvList/{idx}")
	public List<DChattingDomain> DrsvList(@PathVariable("idx") int d_idx) {

		System.out.println("예약 리스트02");

		List<DChattingDomain> list = chattingService.DrsvList(d_idx);

		return list;
	}

	// -- 탑승자 메모 기능 --
	
	@Autowired
	private MemoService memoService;
	
	// [메모] 등록된 카풀 리스트 출력 : 예약이 아직 되지 않은 카풀 등록 리스트
	@RequestMapping("/cpList")
	public List<MemoDomain> cpList() {

		System.out.println("탑승자 메모 01");
		
		// r_comfirm에 따라 카풀 예약 상태가 다름 : 
		// rsv_list가 없거나  r_confirm = null => '지금 예약이 가능합니다!'
		// Y => 예약 불가
		// B => 예약 임박
		List<MemoDomain> cpList = memoService.cpList();

		for (MemoDomain memoDomain : cpList) {
			System.out.println(memoDomain);
		}

		return cpList;

	}

	@Autowired
	private MemoRepository mmRepo;

	// [메모] 카풀 선택하여 메모 작성 : pIdx = 회원번호 / cIdx = 카풀등록번호
	@PostMapping("/writeMemo/{pIdx}/{dIdx}")
	public String writeMemo(@PathVariable("pIdx") int pIdx, @PathVariable("dIdx") int dIdx,
			@RequestParam("memo") String memo) {

		System.out.println("메모 내용" + memo);
		MemoEntity result = mmRepo.save(new MemoEntity(pIdx, dIdx, memo));
		System.out.println(result);

		return result != null ? "success" : "fail";
	}

	// [메모] 메모 수정

	// [메모] 메모 삭제

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
