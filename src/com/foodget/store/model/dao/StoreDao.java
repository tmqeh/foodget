package com.foodget.store.model.dao;

import java.util.List;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.model.StoreDto;

public interface StoreDao {
	public void insertStore(StoreDto storeDto);
	public List<StoreDto> selectStoreList(String keyword);
	public List<BlogDto> mergeBlog(BlogDto blogDto);
	public StoreDto selectStore(StoreDto storeDto);
	public StoreDto selectStore(int store_seq);
	public void insertBlogRank(BlogRankInfoDto blogRankInfoDto);
	public void insertBlogImage(BlogImgInfoDto blogImgInfoDto);
	public int selectStoreSeq(int storeSeq);
	public List<BlogRankInfoDto> selectBlog(int storeSeq);
	public List<String> selectBlogImg(String blogUrl);
}
