package com.foodget.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;

import com.foodget.store.api.JosuChangeApi;
import com.foodget.store.api.NaverApi;
import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.blog.model.SearchDto;
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
	public void insertBlogRank(BlogRankInfoDto blogRankInfoDto) {
		storeDao.insertBlogRank(blogRankInfoDto);
	}

	@Override
	public void insertBlogImage(BlogImgInfoDto blogImgInfoDto) {
		storeDao.insertBlogImage(blogImgInfoDto);
	}
	
	@Override
	public List<StoreDto> StoreSaveAndLoad(String storeinfo) {
		List<StoreDto> slist = new ArrayList<StoreDto>();
		StoreDto storeDto = null;
		JSONObject json = new JSONObject();
		StringTokenizer st = new StringTokenizer(storeinfo, "|");
		String content;
		int j = 0;
		int size = st.countTokens();
		System.out.println("size >>>>>>>>>>>> " + size);
		for(int i = 0 ; i < size ; i++) {
			content = st.nextToken();
			json = StringMethod.getStringMethod().stringToJson(content);
			String storeAddress=json.get("storeAddress")+"";
			String storeName = json.get("storeName")+"";
			storeDto = new StoreDto();
			
			storeDto.setStore_name(storeName);
//			System.out.println(i + " 번째 가게 : " + storeDto.getStore_name());
//			System.out.println( "-------------------------"+ j++ +"-----------------------");
			String store_category = NaverApi.naverOpenApiSearchArea(StringMethod.getStringMethod().cutThatDong(storeAddress)+" "+storeName);
			storeDto.setStore_address(storeAddress);
			storeDto.setStore_phone(json.get("storePhone")+"");
			storeDto.setStore_latitude(Double.parseDouble(json.get("storeLatitude")+""));
			storeDto.setStore_longitude(Double.parseDouble(json.get("storeLongitude")+""));
			storeDto.setStore_category(store_category);
			storeDao.insertStore(storeDto);				
			storeDto = storeDao.selectStore(storeDto);
			slist.add(storeDto);
		}
		return slist;
	}

	@Override
	public StoreDto selectStore(int store_seq) {
		return storeDao.selectStore(store_seq);
	}

	@Override
	public void insertKeyword(String keyword) {
		storeDao.insertKeyword(keyword);
	}

	@Override
	public List<SearchDto> getSearchList(String keyword) {
		return storeDao.getSearchList(keyword);	
	}
	
	public int selectStoreSeq(int storeSeq) {
		return storeDao.selectStoreSeq(storeSeq);
	}

	@Override
	public List<BlogRankInfoDto> selectBlog(int storeSeq) {
		return storeDao.selectBlog(storeSeq);
	}

	@Override
	public List<String> selectBlogImg(String blogUrl) {
		return storeDao.selectBlogImg(blogUrl);
	}

	@Override
	public List<BlogRankInfoDto> showBlogOfStore(int store_seq) {
		StoreDto storeDto = selectStore(store_seq);
		int storeSeq = storeDto.getStore_seq();
		int selectStoreSeq = selectStoreSeq(storeSeq);
		System.out.println("seq  :"+storeSeq);
		List<BlogDto> blogList=null;
		if(selectStoreSeq==0){
			String newAddress= JosuChangeApi.getNewAddres(StringMethod.getStringMethod().cutThatDong(storeDto.getStore_address()));
			String storeTitle = storeDto.getStore_name();
			String searchKeyword = StringMethod.getStringMethod().cutThatDong(JosuChangeApi.getNewAddres(storeDto.getStore_address()))+" "+storeTitle;
			System.out.println("블로그 검색 키워드 :"+searchKeyword);
			System.out.println(">>>>블로그 파싱 시작");
			blogList = NaverApi.getNaverApi().blogInfo(searchKeyword,storeDto.getStore_phone(),storeSeq,newAddress);
			for(int i=0;i<blogList.size();i++){
				BlogDto blogDto = new BlogDto();
				blogDto = blogList.get(i);
				BlogRankInfoDto	blogRankInfoDto = blogDto.getBlogRankInfoDto();
				mergeBlog(blogDto);
				insertBlogRank(blogRankInfoDto);
				BlogImgInfoDto blogImgInfoDto = blogDto.getBlogRankInfoDto().getBlogImgInfoDto();
				insertBlogImage(blogImgInfoDto);
			}
			System.out.println("끝");
		}else{
		}
		List<BlogRankInfoDto> blogRankList=null;
		blogRankList = selectBlog(storeSeq);
		int blogRankListSize = blogRankList.size();
		List<String> imgList=null;
		
		for(int i=0;i<blogRankListSize;i++){
			BlogRankInfoDto blogRankInfoDto = new BlogRankInfoDto();
			blogRankInfoDto = blogRankList.get(i);
			BlogImgInfoDto blogImgInfoDto = new BlogImgInfoDto();
			imgList = selectBlogImg(blogRankInfoDto.getUrl());
			blogImgInfoDto.setImgSrcList(imgList);
			blogRankInfoDto.setBlogImgInfoDto(blogImgInfoDto);
			blogRankList.add(blogRankInfoDto);
		}
		return blogRankList;
	}
}
