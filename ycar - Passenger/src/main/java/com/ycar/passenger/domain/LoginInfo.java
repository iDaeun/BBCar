package com.ycar.passenger.domain;
// session에 저장할 객체
public class LoginInfo {
	private int idx;
	private String id;
	private String nickname;
	
	public LoginInfo(int idx, String id, String nickname) {
		this.idx = idx;
		this.id = id;
		this.nickname = nickname;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
