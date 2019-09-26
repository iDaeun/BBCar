package com.ycar.passenger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ycar.passenger.entity.DCarpoolEntity;

public interface CarPoolRepository extends JpaRepository<DCarpoolEntity, Long> {

	// 등록된 카풀 리스트 출력 : 예약이 아직 되지 않은 카풀 등록 리스트
	@Query("select d from DCarpoolEntity d join RsvEntity r on d.dr_idx = r.dr_idx where r.r_confirm is null order by d.d_date desc")
	public List<DCarpoolEntity> list();
	
}
