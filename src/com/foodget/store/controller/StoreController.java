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
import com.foodget.store.api.TmapApi;
import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.model.StoreDto;
import com.foodget.util.parsing.MapParsing;
import com.foodget.utill.StringMethod;
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
		int storeSeq = storeDto.getStore_seq();
		int selectStoreSeq = storeService.selectStoreSeq(storeSeq);
		System.out.println("seq  :"+storeSeq);
		System.out.println("디비에서 가져온 값 :"+selectStoreSeq);
		List<BlogDto> blogList=null;
		if(selectStoreSeq==0){
			String newAddress= StringMethod.getStringMethod().stringToken(JosuChangeApi.getNewAddres(storeDto.getStore_address()));
			String storeTitle = storeDto.getStore_name();
			String searchKeyword = newAddress+" "+storeTitle;
			System.out.println(">>>>블로그 파싱 시작");
			blogList = NaverApi.getNaverApi().blogInfo(searchKeyword,storeDto.getStore_phone(),storeSeq);
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
		}else{
			System.out.println("노 파싱");
		}
		List<BlogRankInfoDto> blogRankList=null;
		blogRankList = storeService.selectBlog(storeSeq);
		int blogRankListSize = blogRankList.size();
		List<String> imgList=null;
		for(int i=0;i<blogRankListSize;i++){
			BlogRankInfoDto blogRankInfoDto = new BlogRankInfoDto();
			blogRankInfoDto = blogRankList.get(i);
			BlogImgInfoDto blogImgInfoDto = new BlogImgInfoDto();
			imgList = storeService.selectBlogImg(blogRankInfoDto.getUrl());
			System.out.println(blogRankInfoDto.getUrl()+": "+imgList.size());
			blogImgInfoDto.setImgSrcList(imgList);
			blogRankInfoDto.setBlogImgInfoDto(blogImgInfoDto);
			blogRankList.add(blogRankInfoDto);
//			System.out.println(blogImgInfoDto.toString());
		}
		mav.addObject("blogRankList", blogRankList);
		mav.setViewName("/store/storeinfo");
		return mav;
	}
	
	@RequestMapping(value="/bloglist.html", method=RequestMethod.POST)
	public ModelAndView blogList(@RequestParam("blogSearch") String blogSearch, @RequestParam("store_phone") String store_phone){
		ModelAndView mav = new ModelAndView();
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
	@RequestMapping(value="tmapdistance.html")
	public ModelAndView tmapdistance(@RequestParam("endX") String endX,@RequestParam("endY") String endY,@RequestParam("startX") String startX,@RequestParam("startY") String startY )
	{
		ModelAndView mav = new ModelAndView();
		String distance = TmapApi.getTmapApi().getDistance(endX, endY, startX, startY);
		mav.addObject("distance", distance);
		mav.setViewName("/hojin_Test/viewImg");
		return mav;
	}
}
