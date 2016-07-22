package com.foodget.store.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.foodget.store.api.JosuChangeApi;
import com.foodget.store.api.NaverApi;
import com.foodget.store.api.TmapApi;
import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogImgInfoDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.store.blog.model.SearchDto;
import com.foodget.store.model.StoreDto;
import com.foodget.util.parsing.MapParsing;
import com.foodget.utill.ApiTest;
import com.foodget.utill.Encoder;
import com.foodget.utill.StringMethod;
import com.foodget.store.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {
	static{
		System.setProperty("jsse.enableSNIExtension", "false") ; 
	}
	
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
		List<BlogRankInfoDto> blogRankList = storeService.showBlogOfStore(store_seq);
		mav.addObject("blogRankList", blogRankList);
		mav.setViewName("/store/storeinfo");
		return mav;
	}
	@RequestMapping(value="/autoSearch.html", method=RequestMethod.POST)
	public @ResponseBody String storesearch(@RequestParam("keyword")String keyword, @RequestParam("first")String first) {
		JSONObject json = new JSONObject();
		JSONArray jsonarr = new JSONArray();
		System.out.println(keyword);
		
		if("first".equals(first)) {
			searchlist = storeService.getSearchList(keyword);
			for(SearchDto searchdto : searchlist) {
				JSONObject jo = new JSONObject();
				jo.put("keyword", searchdto.getKeyword());
				jsonarr.add(jo);
			}
		} else {
			searchlist = storeService.getSearchList(keyword);
			for(SearchDto searchdto : searchlist) {
				String str = searchdto.getKeyword();
				if(str.toUpperCase().startsWith(keyword.toUpperCase())) {
					JSONObject jo = new JSONObject();
					jo.put("keyword", searchdto.getKeyword());
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
		return mav;
	}
	@RequestMapping(value="viewImg.html", method=RequestMethod.POST )
	public ModelAndView imgList(@RequestParam("blogSrc") String blogSrc){
		ModelAndView mav = new ModelAndView();
		MapParsing.getMapParsing().startParsing(blogSrc);
		List<String> list = MapParsing.getMapParsing().getBlogDto().getBlogRankInfoDto().getBlogImgInfoDto().getImgSrcList();
		mav.addObject("list", list);
		mav.setViewName("/hojin_Test/viewImg");
		return mav;
	}
	@RequestMapping(value="tmapdistance.html")
	public void tmapdistance(@RequestParam("endX") String endX,@RequestParam("endY") String endY,@RequestParam("startX") String startX,@RequestParam("startY") String startY,HttpServletResponse response )
	{
		String distance = TmapApi.getTmapApi().getDistance(endX, endY, startX, startY);
		try {
			response.getWriter().print(distance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="getroot.html")
	public void getRoot(@RequestParam("apikey") String apikey,@RequestParam("q") String q,@RequestParam("output") String output,HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		try {
			q = new String(q.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String json=ApiTest.getApiTest().addressToLocation(apikey, q);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
