package com.foodget.utill;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class StringMethod {
	static StringMethod stringMethod;
	static{
		stringMethod = new StringMethod();
	}
	public static StringMethod getStringMethod(){
		return stringMethod;
	}
	public static String nullToBlank(String tmp){
		return tmp == null ? "" : tmp;
	}
	public String getBlogIdToken(String str){
		StringTokenizer strToken = new StringTokenizer(str, "?");
		int strTokenCount = strToken.countTokens();
		for(int i=0;i<strTokenCount;i++){
			str  = strToken.nextToken();
			if(i==strTokenCount-1){
				StringTokenizer strToken2 = new StringTokenizer(str, "&");
				int strToken2Count = strToken2.countTokens();
				for(int j=0;j<strToken2Count;j++){
					str = strToken2.nextToken();
					StringTokenizer strToken3 = new StringTokenizer(str, "=");
					str = strToken3.nextToken();
					if("blogId".equals(str)){
						str=strToken3.nextToken();
						return str;
					}
				}
			}
		}
		return "";
	}
	public  void stringToTextFile(String str){
		try
        {
			String root = "c:\\here.txt";
            FileWriter fw = new FileWriter(root);  
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(str);	
            bw.close();
        }
        catch (IOException e)
        {
            System.err.println(e);  
            System.out.println(" Method : stringToTextFile");
            System.exit(1);
        }
	}
	public boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public boolean lastCharNumberCheck(String str){
		char temp[] = str.toCharArray();
		int length = str.length();
		for(int i=0;i<length;i++){
			if(isStringDouble(temp[length-1]+"")){
				
			}else{
				return false;
			}
		}
		return true;
	}
	public static String stringToken(String str) {
		str = str.replace("-", "");
		StringTokenizer strToken = new StringTokenizer(str, " ");
		int count = strToken.countTokens();
		for (int i = 0; i < count; i++) {
			String tokenItem = strToken.nextToken();
			if (stringMethod.isStringDouble(tokenItem) || stringMethod.lastCharNumberCheck(tokenItem)) {
				if(strToken.hasMoreTokens()){
					while (strToken.hasMoreTokens()) {
						str = str.replace(tokenItem, "");
						tokenItem = strToken.nextToken();
						str = str.replace(tokenItem, "");
					}
				}else{
					str = str.replace(tokenItem, "");
				}
				break;
			}
		}
		return str;
	}
	public String getKeyAndValToUrl(String str, String key){
		StringTokenizer strToken = new StringTokenizer(str, "?");
		int size = strToken.countTokens();
		String value="";
		for (int i = 0; i < size; i++) {
			str = strToken.nextToken();
			if(!strToken.hasMoreTokens()){
				strToken = new StringTokenizer(str, "&");
				int sizek = strToken.countTokens();
				for(int k=0;k<sizek;k++){
					str = strToken.nextToken();
					StringTokenizer strTokenK = new StringTokenizer(str, "=");
					int sizej = strTokenK.countTokens();
					for(int j=0;j<sizej;j++){
						key = strTokenK.nextToken();
						if("logNo".equals(key)){
							value = strTokenK.nextToken();
							break;
						}
					}
				}
			}
		}
		return value;
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
