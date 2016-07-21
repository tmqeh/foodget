package com.foodget.store.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.foodget.store.blog.model.BlogDto;
import com.foodget.store.blog.model.BlogRankInfoDto;
import com.foodget.util.parsing.MapParsing;
import com.foodget.util.parsing.XmlParseDom;
import com.foodget.utill.Encoder;

public class NaverApi {
	static NaverApi naverApi;
	static{
		naverApi = new NaverApi();
	}
	public static NaverApi getNaverApi() {
		return naverApi;
	}
	public static Document naverOpenApiSearchBlog(String query){
		Document document =null;
		String xml="";
		HttpResponse res = null;
		String clientId = "vFmv_WyXnF1xS_8ZmKoW";
		String clientSecret = "GGraX8OAa8";
		query = Encoder.eucUrl(query);
		int display=100;
		String url = "https://openapi.naver.com/v1/search/blog.xml?query=" + query + "&display="+display+"&start=1&sort=sim";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet req = new HttpGet(url);// reqest 占쏙옙체
		req.addHeader("X-Naver-Client-Id", clientId);
		req.addHeader("X-Naver-Client-Secret", clientSecret);
		req.addHeader("Host", "openapi.naver.com");
		req.addHeader("User-Agent", "curl/7.43.0");
		req.addHeader("Accept", "*/*");
		req.addHeader("Content-Type", "application/xml");
		try {
			res = client.execute(req);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			sb = new StringBuilder();
			sb.append("<item><message>에러</message></item>");
		}
		xml = sb.toString();
		document = XmlParseDom.createDocument(xml);
		return document;
	}
	public List<BlogDto> blogInfo(String query, String store_phone, int store_seq,String newAddress){
		MapParsing mapParsing = MapParsing.getMapParsing();
		Document document = naverOpenApiSearchBlog(query);//블로그 검색
		NodeList description =  XmlParseDom.getNodeList("//item/description", document);
		NodeList title =  XmlParseDom.getNodeList("//item/title", document);
		NodeList link = XmlParseDom.getNodeList("//item/link", document);
		List<BlogDto> blogList = new ArrayList<BlogDto>();
		for (int i = 0; i < link.getLength(); i++) {
			if(mapParsing.startParsing(link.item(i).getTextContent())){
				BlogDto blogDto = new BlogDto();
				blogDto = MapParsing.getMapParsing().getBlogDto();
				if(newAddress.equals(blogDto.getNew_address())){
					blogDto.setDescription(description.item(i).getTextContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
					blogDto.setTitle(title.item(i).getTextContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
					blogDto.setStore_phone(store_phone);
					blogDto.setStore_seq(store_seq);
					blogList.add(blogDto);
				}
			}
		}
		return blogList;
	}

	public static String naverOpenApiSearchArea(String query){
		String getTitle="";
		Document document =null;
		String xml="";
		HttpResponse res = null;
		String clientId = "vFmv_WyXnF1xS_8ZmKoW";
		String clientSecret = "GGraX8OAa8";
		if(query==null){
			return getTitle;
		}
		query = Encoder.eucUrl(query);
		String url = "https://openapi.naver.com/v1/search/local.xml?query="+query+"&display=10&start=1&sort=random";
		HttpClient client = HttpClientBuilder.create().build();// 占쏙옙청占쏙옙체
		HttpGet req = new HttpGet(url);// reqest 占쏙옙체
		req.addHeader("X-Naver-Client-Id", clientId);
		req.addHeader("X-Naver-Client-Secret", clientSecret);
		req.addHeader("Host", "openapi.naver.com");
		req.addHeader("User-Agent", "curl/7.43.0");
		req.addHeader("Accept", "*/*");
		req.addHeader("Content-Type", "application/xml");
		try {
			res = client.execute(req);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception e) {
			sb = new StringBuilder();
			sb.append("<item><message>에러</message></item>");
		}
		xml = sb.toString();
		document = XmlParseDom.xmlPeraseDocument(xml);
		NodeList title = XmlParseDom.getNodeList("//item/category", document);
		if(title.item(0) !=null){
			getTitle = title.item(0).getTextContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
		}else{
			getTitle="";
		}
			
//	    for (int i = 0; i < link.getLength(); i++) {
//			if(MapParsing.startParsing(link.item(i).getTextContent())){
//				System.out.println(i+".[O]"+link.item(i).getTextContent());
//				cnt++;
//			}else{
//				System.out.println(i+".[X]"+link.item(i).getTextContent());
//			}
//		}
	    return getTitle;
	}
}
