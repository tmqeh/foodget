package com.foodget.store.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.model.StoreDto;

public class StoreDaoImpl implements StoreDao {
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertStore(StoreDto storeDto) {		
		int cnt = sqlSession.selectOne("com.foodget.store.model.dao.StoreDaoImpl.storeCheck", storeDto);
		if(cnt == 0) {
			sqlSession.insert("com.foodget.store.model.dao.StoreDaoImpl.storeInsert", storeDto);	
		}
	}

	@Override
	public StoreDto selectStore(StoreDto storeDto) {
		return sqlSession.selectOne("com.foodget.store.model.dao.StoreDaoImpl.storeSearch", storeDto);
	}

	@Override
	public List<BlogDto> mergeBlog(BlogDto blogDto) {
		return sqlSession.selectOne("com.foodget.store.model.dao.StoreDaoImpl.mergeBlog",blogDto);
	}

	@Override
	public List<StoreDto> selectStoreList(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StoreDto selectStore(int store_seq) {
		return sqlSession.selectOne("com.foodget.store.model.dao.StoreDaoImpl.storeInfo",store_seq);
	}

}
