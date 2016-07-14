package com.foodget.member.model.dao;

import java.util.Map;

import com.foodget.member.model.MemberDto;

public interface MemberDao {
	public void join(MemberDto memberDto);
	public MemberDto login(Map map);
	public int idCheck(String email);
	public void emailauth(String email);
}
