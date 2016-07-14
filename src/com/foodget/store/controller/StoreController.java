package com.foodget.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.foodget.member.model.MemberDto;
import com.foodget.store.api.JosuChangeApi;
import com.foodget.store.api.NaverApi;
import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.model.StoreDto;
import com.foodget.util.parsing.MapParsing;
import com.foodget.store.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	private StoreService storeService;
	
	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	@RequestMapping(value="/storeinsert.html", method=RequestMethod.POST)
	public ModelAndView store(@RequestParam("storeinfo")String storeinfo, @RequestParam("addresskeyword")String keyword) {
//		System.out.println("storeinfo = " + storeinfo);
		ModelAndView mav = new ModelAndView();
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
	
	@RequestMapping(value="/bloglist.html", method=RequestMethod.POST)
	public ModelAndView blogList(@RequestParam("blogSearch") String blogSearch){
		ModelAndView mav = new ModelAndView();
		System.out.println("시작");
		List<BlogDto> blogList = NaverApi.getNaverApi().blogInfo(blogSearch);
	
		for(int i=0;i<blogList.size();i++){
			BlogDto blogDto = new BlogDto();
			blogDto = blogList.get(i);
			storeService.mergeBlog(blogDto);
		}
//		JosuChangeApi.getJosuChangeApi().ChangeToNewAddress(blogList);
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
