package com.foodget.store.service;

import java.util.List;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.blog.model.SearchDto;
import com.foodget.store.model.StoreDto;

public interface StoreService {
	public List<BlogDto> mergeBlog(BlogDto blogDto);
	public void insertStore(StoreDto storeDto);
	public StoreDto selectStore(StoreDto storeDto);
	public StoreDto selectStore(int store_seq);
	public void insertBlogRank(BlogRankInfoDto blogRankInfoDto);
	public void insertBlogImage(BlogImgInfoDto blogImgInfoDto);
	public void insertKeyword(String keyword);
	public List<SearchDto> getSearchList(String keyword);
	public List<StoreDto> StoreSaveAndLoad(String storeinfo);	
	public int selectStoreSeq(int storeSeq);
	public List<BlogRankInfoDto> selectBlog(int storeSeq);
	public List<String> selectBlogImg(String blogUrl);
	public List<BlogRankInfoDto> showBlogOfStore(int store_seq);
}
