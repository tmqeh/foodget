package com.foodget.member.model.dao;

import java.util.Map;

import com.foodget.member.model.MemberDto;

public interface MemberDao {
	public void join(MemberDto memberDto);
	public void kakaojoin (MemberDto memberDto);
	public MemberDto login(Map map);
	public MemberDto kakaologin(Map map);
	public int idCheck(String email);
	public void emailauth(String email);
	public void modify(MemberDto memberDto);
}
