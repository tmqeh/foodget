package com.foodget.utill;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encoder {
	
	public static String isoToEuc(String tmp) {
		String euc = "";
		try {
			if(tmp != null)
				euc = new String(tmp.getBytes("ISO-8859-1"), BoardConstance.DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	public static String isoToUtf(String tmp) {
		String euc = "";
		try {
			if(tmp != null)
				euc = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	public static String eucUrl(String tmp){
		String euc="";
		try {
			euc = URLEncoder.encode(tmp, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return euc;
	}
	public static String isoToUTF(String tmp) {
		String euc = "";
		try {
			if(tmp != null)
				euc = new String(tmp.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	
	public static String eucToUTF(String tmp) {
		String euc = "";
		try {
			if(tmp != null)
				euc = new String(tmp.getBytes("EUC-KR"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	public static String utfToEuc(String tmp) {
		String euc = "";
		try {
			if(tmp != null)
				euc = new String(tmp.getBytes("UTF-8"), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return euc;
	}
	

}
