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
		if(memberDto.getMember_saveimg() != null) {
			System.out.println("카카오 회원 가입");
			memberDao.kakaojoin(memberDto);
		} else {
			System.out.println("일반 회원 가입");
			memberDao.join(memberDto);			
		}
	}

	@Override
	public MemberDto login(MemberDto memberDto, String kakaoflag) {
		Map<String, String> map = new HashMap<String, String>();
		if(kakaoflag.equals("kakao")) {
			System.out.println("카카오 로그인");
			map.put("email", memberDto.getEmail());
			return memberDao.kakaologin(map);
		} else {
			System.out.println("일반 로그인");
			map.put("email", memberDto.getEmail());
			map.put("password", memberDto.getPassword());			
			return memberDao.login(map);
		}
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
