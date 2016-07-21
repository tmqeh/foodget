package com.foodget.store.controller;


import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.foodget.store.api.NaverApi;
import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.blog.model.SearchDto;
import com.foodget.store.model.StoreDto;
import com.foodget.util.parsing.MapParsing;
import com.foodget.store.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	private StoreService storeService;
	
	private List<SearchDto> searchlist;
	
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}
	@RequestMapping(value="/storeinsert.html", method=RequestMethod.POST)
	public ModelAndView store(@RequestParam("storeinfo")String storeinfo, @RequestParam("keyword")String keyword) {
		ModelAndView mav = new ModelAndView();
		storeService.insertKeyword(keyword);
		List<StoreDto> slist = storeService.StoreSaveAndLoad(storeinfo);
		mav.addObject("slist", slist);
		mav.setViewName("/search");		
		return mav;
	}

	@RequestMapping(value="/storeInfo.html", method=RequestMethod.POST)
	public ModelAndView storeInfo(@RequestParam("store_seq")int store_seq) {
		ModelAndView mav = new ModelAndView();
		StoreDto storeDto = storeService.selectStore(store_seq);
		mav.addObject("storeInfo", storeDto);
		mav.setViewName("/store/storeinfo");
		return mav;
	}
	
	@RequestMapping(value="/autoSearch.html", method=RequestMethod.POST)
	public @ResponseBody String storesearch(@RequestParam("keyword")String keyword, @RequestParam("first")String first) {
		JSONObject json = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		
		if("first".equals(first)) {
			searchlist = storeService.getSearchList(keyword);
			System.out.println("크기" + searchlist.size());
			for(SearchDto searchdto : searchlist) {
				JSONObject jo = new JSONObject();
				jo.put("keyword", searchdto.getKeyword());
				System.out.println(jo.get("keyword"));
				jsonarr.add(jo);
			}
		} else {
			searchlist = storeService.getSearchList(keyword);
			for(SearchDto searchdto : searchlist) {
				String str = searchdto.getKeyword();
				if(str.toUpperCase().startsWith(keyword.toUpperCase())) {
					JSONObject jo = new JSONObject();
					jo.put("keyword", searchdto.getKeyword());
					System.out.println("else " + jo.get("keyword"));
					jsonarr.add(jo);
				}
			}
		}
		json.put("keylist", jsonarr);
		return json.toJSONString();
	}
	
	@RequestMapping(value="/bloglist.html", method=RequestMethod.POST)
	public ModelAndView blogList(@RequestParam("blogSearch") String blogSearch, @RequestParam("store_phone") String store_phone){
		ModelAndView mav = new ModelAndView();
		System.out.println("시작");
		List<BlogDto> blogList = NaverApi.getNaverApi().blogInfo(blogSearch,store_phone);
		for(int i=0;i<blogList.size();i++){
			BlogDto blogDto = new BlogDto();
			blogDto = blogList.get(i);
			BlogRankInfoDto	blogRankInfoDto = blogDto.getBlogRankInfoDto();
			storeService.mergeBlog(blogDto);
			storeService.insertBlogRank(blogRankInfoDto);
			BlogImgInfoDto blogImgInfoDto = blogDto.getBlogRankInfoDto().getBlogImgInfoDto();
			storeService.insertBlogImage(blogImgInfoDto);
		}
		System.out.println("끝");
		mav.addObject("blogList", blogList);
		mav.setViewName("/blogList");
		return mav;
	}
	@RequestMapping(value="viewImg.html", method=RequestMethod.POST )
	public ModelAndView imgList(@RequestParam("blogSrc") String blogSrc){
		ModelAndView mav = new ModelAndView();
		MapParsing.getMapParsing().startParsing(blogSrc);
		List<String> list = MapParsing.getMapParsing().getBlogDto().getBlogRankInfoDto().getBlogImgInfoDto().getImgSrcList();
		System.out.println("블로그 사진 갯수 :"+list.size());
		mav.addObject("list", list);
		mav.setViewName("/hojin_Test/viewImg");
		return mav;
	}
}
