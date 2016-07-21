package com.foodget.utill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;

import com.foodget.util.parsing.XmlParseDom;

public class ApiTest {
	static ApiTest apiTest;
	static{
		apiTest = new ApiTest();
	}
	
	public static ApiTest getApiTest() {
		return apiTest;
	}
	public String addressToLocation(String apikey, String keyword){
		Document document =null;
		String data="";
		String json="";
		HttpResponse res = null;
		String query = Encoder.eucUrl(keyword);
		String url = "https://apis.daum.net/local/geo/addr2coord?apikey="+apikey+"&q="+query+"&output=json";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet req = new HttpGet(url);// reqest 占쏙옙체
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
		data = sb.toString();
		JSONObject j = StringMethod.getStringMethod().stringToJson(data);
		JSONObject j2 =StringMethod.getStringMethod().stringToJson(j.get("channel").toString());
		JSONObject j3 =StringMethod.getStringMethod().stringToJson(j2.get("item").toString().replace("[", "").replace("]", ""));
		System.out.println(j3.get("point_x"));
		System.out.println(j3.get("point_y"));
		json = "{\"point_x\":\""+j3.get("point_x")+"\", \"point_y\":\""+j3.get("point_y")+"\"}";
//		System.out.println(j2.get("newAddress"));
		return json;
	}
	
}
