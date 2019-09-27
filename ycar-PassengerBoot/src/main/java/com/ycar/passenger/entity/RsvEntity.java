package com.ycar.passenger.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

// 카풀 예약 table
@Entity
@Table(name = "RESERVATION")
public class RsvEntity {

	@Id
	@Column(name = "dr_idx")
	private int dr_idx;

	@Column
	private char r_confirm;

	@Column
	private int r_idx;

	public int getR_idx() {
		return r_idx;
	}

	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}

	@ManyToOne
	//@JoinColumn(name = "dr_idx", insertable = false, updatable = false)
	@JoinColumn(name = "dr_idx", insertable = false, updatable = false)
	private DCarpoolEntity dcp;

	public DCarpoolEntity getDcp() {
		return dcp;
	}

	public void setDcp(DCarpoolEntity dcp) {
		
		//dcp.getRsvlist().add(this);
		
		this.dcp = dcp;
	}

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

	@Override
	public String toString() {
		return "RsvEntity [dr_idx=" + dr_idx + ", r_confirm=" + r_confirm + ", r_idx=" + r_idx + "]";
	}

}
