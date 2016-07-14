package com.foodget.store.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.w3c.dom.Document;

import com.foodget.util.parsing.XmlParseDom;
import com.foodget.utill.Encoder;
import com.foodget.utill.StringMethod;

public class TmapApi {
	public static void main(String[] args) {
//		System.out.println(StringMethod.getStringMethod().StringToKm(new TmapApi().getDistance("","","","")));
		System.out.println(StringMethod.getStringMethod().StringToKm("1111"));
	}
	public String getDistance(String endX, String endY, String startX, String startY){
		System.setProperty("jsse.enableSNIExtension", "false") ; 
		String jsonStr="";
		HttpResponse res = null;
		endX="14363856.085492350";
		endY="4178405.946508492";
		startX="14135591.321772";
		startY="4518111.822511";
//		String endX="35.3447730";
//		String endY="129.0199550";
//		String startX="37.376385";
//		String startY="126.635564";
		String url = "https://apis.skplanetx.com/tmap/routes?version=1&endX="+endX+"&endY="+endY+"&startX="+startX+"&startY="+startY+"";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet req = new HttpGet(url);// reqest 占쏙옙체
		req.addHeader("Host", "apis.skplanetx.com");
		req.addHeader("appKey", "5064adfe-57cd-35d4-b5dd-3d46c557ad0e");
		req.addHeader("Accept-Language", "ko");
		req.addHeader("Content-Type", "application/json");
		try {
			res = client.execute(req);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
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
		jsonStr = sb.toString();
		JSONObject json = StringMethod.getStringMethod().stringToJson(jsonStr);
		JSONArray jsonArray= (JSONArray) json.get("features");
		json = (JSONObject)jsonArray.get(0);
		json = (JSONObject)json.get("properties");
		
		return json.get("totalDistance").toString();
	}
}
