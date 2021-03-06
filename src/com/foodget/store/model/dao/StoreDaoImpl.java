package com.foodget.store.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.blog.model.SearchDto;
import com.foodget.store.model.StoreDto;

public class StoreDaoImpl implements StoreDao {
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insertStore(StoreDto storeDto) {		
		int cnt = sqlSession.selectOne("com.foodget.store.model.dao.StoreDaoImpl.storeCheck", storeDto);
//		System.out.println("cnt 값 : "+cnt);
//		System.out.println(storeDto.toString());
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
		return null;
	}

	@Override
	public StoreDto selectStore(int store_seq) {
		return sqlSession.selectOne("com.foodget.store.model.dao.StoreDaoImpl.storeInfo",store_seq);
	}
	@Override
	public void insertBlogRank(BlogRankInfoDto blogRankInfoDto) {
		sqlSession.insert("com.foodget.store.model.dao.StoreDaoImpl.insertBlogRank", blogRankInfoDto);	
	}

	@Override
	public void insertBlogImage(BlogImgInfoDto blogImgInfoDto) {
		sqlSession.insert("com.foodget.store.model.dao.StoreDaoImpl.insertBlogImage", blogImgInfoDto);	
	}

	@Override
	public void insertKeyword(String keyword) {
		sqlSession.insert("com.foodget.store.model.dao.StoreDaoImpl.insertKeyword", keyword);	
	}

	@Override
	public List<SearchDto> getSearchList(String keyword) {
		return sqlSession.selectList("com.foodget.store.model.dao.StoreDaoImpl.search", keyword);
	}

	public int selectStoreSeq(int storeSeq) {
		return sqlSession.selectOne("com.foodget.store.model.dao.StoreDaoImpl.selectStoreSeq", storeSeq);	
	}

	@Override
	public List<BlogRankInfoDto> selectBlog(int storeSeq) {
		return sqlSession.selectList("com.foodget.store.model.dao.StoreDaoImpl.selectBlog", storeSeq);
	}

	@Override
	public List<String> selectBlogImg(String blogUrl) {
		return sqlSession.selectList("com.foodget.store.model.dao.StoreDaoImpl.selectBlogImg", blogUrl);
	}

	@Override
	public void updateStoreImg(StoreDto storeDto) {
		sqlSession.selectList("com.foodget.store.model.dao.StoreDaoImpl.updateStoreImg", storeDto);
	}


}
