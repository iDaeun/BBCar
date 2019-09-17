package com.ycar.pClient.domain;
// 마이페이지에 띄울 내 정보
public class MypageInfo {
	private String name;
	private String id;
	private String email;
	private String nickname;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "MypageInfo [name=" + name + ", id=" + id + ", email=" + email + ", nickname=" + nickname + "]";
	}
	
	
}
