package com.foodget.member.service;

import java.util.Map;

import com.foodget.member.model.MemberDto;

public interface MemberService {
	public void join(MemberDto memberDto);
	public MemberDto login(MemberDto memberDto, String kakaoflag);
	public int idCheck(String email);
	public void emailauth(String email);
	public void modify(MemberDto memberDto);
}
