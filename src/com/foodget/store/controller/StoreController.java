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
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
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
		List<StoreDto> slist = new ArrayList<StoreDto>();
		StringTokenizer st = new StringTokenizer(storeinfo, "|");
		String content;
		
		StoreDto storeDto = null;
		JSONObject json = new JSONObject();
		for(int i = 0 ; i < 14 ; i++) {
			storeDto = new StoreDto();
			content = st.nextToken();
			json = stringToJson(content);
			storeDto.setStore_name(json.get("storeName")+"");
			storeDto.setStore_address(json.get("storeAddress")+"");
			storeDto.setStore_phone(json.get("storePhone")+"");
			storeDto.setStore_latitude(Double.parseDouble(json.get("storeLatitude")+""));
			storeDto.setStore_logitude(Double.parseDouble(json.get("storeLongitude")+""));
			
			storeService.insertStore(storeDto);			
			storeDto = storeService.selectStore(storeDto);
			slist.add(storeDto);
		}
		System.out.println("크기 : " + slist.size());
		mav.addObject("slist", slist);
		mav.setViewName("/search");
		return mav;
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
	
	public JSONObject stringToJson(String data) {
	      JSONObject json = new JSONObject();
	      Object obj = null;
	      try {
	         obj = JSONValue.parseWithException(data);
	         json = (JSONObject) obj;
	      } catch (ParseException e) {
	         e.printStackTrace();
	      }
	      return json;
	   }
}
