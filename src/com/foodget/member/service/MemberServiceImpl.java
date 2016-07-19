package com.foodget.member.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foodget.member.model.MemberDto;
import com.foodget.member.model.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public void join(MemberDto memberDto) {
		memberDao.join(memberDto);		
	}

	@Override
	public MemberDto login(String email, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", password);
		return memberDao.login(map);
	}

	@Override
	public int idCheck(String email) {
		return memberDao.idCheck(email);
	}

	@Override
	public void emailauth(String email) {
		memberDao.emailauth(email);		
	}

	@Override
	public void modify(MemberDto memberDto) {
		memberDao.modify(memberDto);
	}
	
	
}
