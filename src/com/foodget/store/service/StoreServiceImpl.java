package com.foodget.store.service;

import java.util.List;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.model.StoreDto;
import com.foodget.store.model.dao.StoreDao;

public class StoreServiceImpl implements StoreService {
	
	private StoreDao storeDao;

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	@Override
	public void insertStore(StoreDto storeDto) {
		storeDao.insertStore(storeDto);
	}

	@Override
	public StoreDto selectStore(StoreDto storeDto) {
		return storeDao.selectStore(storeDto);
	}

	@Override
	public List<BlogDto> mergeBlog(BlogDto blogDto) {
		return storeDao.mergeBlog(blogDto);
	}

	@Override
	public void insertBlogRank(BlogRankInfoDto blogRankInfoDto) {
		storeDao.insertBlogRank(blogRankInfoDto);
	}

	@Override
	public void insertBlogImage(BlogImgInfoDto blogImgInfoDto) {
		storeDao.insertBlogImage(blogImgInfoDto);
	}


}
