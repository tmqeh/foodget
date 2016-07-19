package com.foodget.member.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.foodget.member.model.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void join(MemberDto memberDto) {
		sqlSession.insert("com.foodget.member.model.dao.MemberDaoImpl.join", memberDto);
	}

	@Override
	public MemberDto login(Map map) {
		return sqlSession.selectOne("com.foodget.member.model.dao.MemberDaoImpl.login", map);
	}

	@Override
	public int idCheck(String email) {
		System.out.println("email : " + email);
		return sqlSession.selectOne("com.foodget.member.model.dao.MemberDaoImpl.idCheck", email);
	}

	@Override
	public void emailauth(String email) {
		System.out.println("email22222222 : " + email);
		sqlSession.update("com.foodget.member.model.dao.MemberDaoImpl.emailauth", email);
	}

	@Override
	public void modify(MemberDto memberDto) {
		sqlSession.update("com.foodget.member.model.dao.MemberDaoImpl.modify", memberDto);
	}
	
}
