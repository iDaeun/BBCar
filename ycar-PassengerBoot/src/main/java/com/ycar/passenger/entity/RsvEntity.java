package com.ycar.passenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 카풀 예약 table
@Entity
@Table(name = "RESERVATION")
public class RsvEntity {

	@Id
	@Column(name = "dr_idx")
	private int dr_idx;

	@Column
	private char r_confirm;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "dr_idx", insertable = false, updatable = false) private
	 * DCarpoolEntity dcp;
	 * 
	 * public DCarpoolEntity getDcp() { return dcp; }
	 * 
	 * public void setDcp(DCarpoolEntity dcp) { this.dcp = dcp; }
	 */

	public int getDr_idx() {
		return dr_idx;
	}

	public void setDr_idx(int dr_idx) {
		this.dr_idx = dr_idx;
	}

	public char getR_confirm() {
		return r_confirm;
	}

	public void setR_confirm(char r_confirm) {
		this.r_confirm = r_confirm;
	}

}
