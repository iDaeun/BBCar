package com.ycar.passenger.domain;

public class PassengerInfo {
	private int idx;
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private String email;
	private char gender;
	private char verify;
	private String code;
	
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public char getVerify() {
		return verify;
	}
	public void setVerify(char verify) {
		this.verify = verify;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "PassengerInfo [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", nickname=" + nickname
				+ ", email=" + email + ", gender=" + gender + ", verify=" + verify + ", code=" + code + "]";
	}
	
	public boolean pwMatch(String pw) {
		return this.getPw().equals(pw);
	}
}
