package com.foodget.store.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.util.parsing.XmlParseDom;
import com.foodget.utill.StringMethod;

public class JosuChangeApi {
	static JosuChangeApi josuChangeApi;
	static{
		josuChangeApi = new JosuChangeApi();
	}
	
	public static String getNewAddres(String oldAddress){
		String newAddress="";
		String currentPage = "1";
		String countPerPage = "10";
		String confmKey = "U01TX0FVVEgyMDE2MDcwNTE1Mzg0NzEzNjEx";
		String keyword = oldAddress;
		String apiUrl = null;
		try {
			apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=" + currentPage + "&countPerPage="
					+ countPerPage + "&keyword=" + URLEncoder.encode(keyword, "UTF-8") + "&confmKey=" + confmKey;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(apiUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		String tempStr = null;
		while (true) {
			try {
				tempStr = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (tempStr == null)
				break;
			sb.append(tempStr);
		}
		String xml = sb.toString();
//		System.out.println(xml);
//		StringMethod.getStringMethod().stringToTextFile(xml);
		Document document = XmlParseDom.xmlPeraseDocument(xml);
		NodeList NodeListAddress = XmlParseDom.getNodeList("//juso/jibunAddr", document);
		if(NodeListAddress.item(0)==null){
			newAddress="";
		}else{
			newAddress = NodeListAddress.item(0).getTextContent();
			newAddress = newAddress.replace("(", "").replace(")", "");
		}
		
//		for (int i = 0; i < NodeListAddress.getLength(); i++) {
//			System.out.println(NodeListAddress.item(0).getTextContent());
//		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newAddress;
	}
	public static JosuChangeApi getJosuChangeApi() {
		return josuChangeApi;
	}
	public List<BlogDto> ChangeToNewAddress(List<BlogDto> blogList){
		int size= blogList.size();
		String newAddress="";
		List<BlogDto> newBlogDtoList = new ArrayList<BlogDto>();
		for(int i=0;i<size;i++){
			BlogDto blogDto = blogList.get(i);
			newAddress= StringMethod.getStringMethod().stringToken(getNewAddres(blogDto.getOld_address()));
			blogDto.setNew_address(newAddress);
			blogDto.setSearchWord(newAddress+" "+blogDto.getStore_name());
			newBlogDtoList.add(blogDto);
		}
		return newBlogDtoList;
	}

}
