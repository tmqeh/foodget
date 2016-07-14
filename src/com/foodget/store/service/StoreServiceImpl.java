package com.foodget.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.model.StoreDto;
import com.foodget.store.model.dao.StoreDao;
import com.foodget.utill.StringMethod;

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
	public StoreDto selectStore(int store_seq) {
		return storeDao.selectStore(store_seq);
	}

	@Override
	public List<StoreDto> StoreSaveAndLoad(String storeinfo) {
		List<StoreDto> slist = new ArrayList<StoreDto>();
		StoreDto storeDto = null;
		
		JSONObject json = new JSONObject();
		StringTokenizer st = new StringTokenizer(storeinfo, "|");
		String content;
		for(int i = 0 ; i < 14 ; i++) {
			storeDto = new StoreDto();
			content = st.nextToken();
			json = StringMethod.getStringMethod().stringToJson(content);
			storeDto.setStore_name(json.get("storeName")+"");
			storeDto.setStore_address(json.get("storeAddress")+"");
			storeDto.setStore_phone(json.get("storePhone")+"");
			storeDto.setStore_latitude(Double.parseDouble(json.get("storeLatitude")+""));
			storeDto.setStore_longitude(Double.parseDouble(json.get("storeLongitude")+""));
			
			storeDao.insertStore(storeDto);				
			storeDto = storeDao.selectStore(storeDto);
			slist.add(storeDto);
		}
		return slist;
	}

}
