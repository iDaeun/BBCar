package com.ycar.passenger.domain;

public class MypageEdit {
	private String id;
	private String pw1;
	private String pw2;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw1() {
		return pw1;
	}
	public void setPw1(String pw1) {
		this.pw1 = pw1;
	}
	public String getPw2() {
		return pw2;
	}
	public void setPw2(String pw2) {
		this.pw2 = pw2;
	}
	
	@Override
	public String toString() {
		return "MypageEdit [id=" + id + ", pw1=" + pw1 + ", pw2=" + pw2 + "]";
	}
	
	
	
}
