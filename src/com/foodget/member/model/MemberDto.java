package com.foodget.member.model;

public class MemberDto {
	private String email;
	private String password;
	private String name;
	private String address1;
	private String address2;
	private int email_auth;
	private String member_originimg; 
	private String member_saveimg; 
	private String member_savefolder;
	private int kakao_auth;
	
	public int getKakao_auth() {
		return kakao_auth;
	}
	public void setKakao_auth(int kakao_auth) {
		this.kakao_auth = kakao_auth;
	}
	public int getEmail_auth() {
		return email_auth;
	}
	public void setEmail_auth(int email_auth) {
		this.email_auth = email_auth;
	}
	public String getMember_originimg() {
		return member_originimg;
	}
	public void setMember_originimg(String member_originimg) {
		this.member_originimg = member_originimg;
	}
	public String getMember_saveimg() {
		return member_saveimg;
	}
	public void setMember_saveimg(String member_saveimg) {
		this.member_saveimg = member_saveimg;
	}
	public String getMember_savefolder() {
		return member_savefolder;
	}
	public void setMember_savefolder(String member_savefolder) {
		this.member_savefolder = member_savefolder;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
}
