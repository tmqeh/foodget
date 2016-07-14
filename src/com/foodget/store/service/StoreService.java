package com.foodget.store.service;

import java.util.List;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.model.StoreDto;

public interface StoreService {
	public List<BlogDto> mergeBlog(BlogDto blogDto);
	public void insertStore(StoreDto storeDto);
	public StoreDto selectStore(StoreDto storeDto);
	public StoreDto selectStore(int store_seq);
	public List<StoreDto> StoreSaveAndLoad(String storeinfo);
}
