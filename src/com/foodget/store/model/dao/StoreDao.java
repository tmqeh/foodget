package com.foodget.store.model.dao;

import java.util.List;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.model.StoreDto;

public interface StoreDao {
	public void insertStore(StoreDto storeDto);
	public List<StoreDto> selectStoreList(String keyword);
	public List<BlogDto> mergeBlog(BlogDto blogDto);
	public StoreDto selectStore(StoreDto storeDto);
	public StoreDto selectStore(int store_seq);

}
