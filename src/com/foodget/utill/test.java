package com.foodget.utill;

import com.foodget.store.api.JosuChangeApi;

public class test {

	public static void main(String[] args) {
//		System.out.println(StringMethod.getStringMethod().stringToken("서울 강남구 신사동 588-10 1층"));
		System.out.println(JosuChangeApi.getNewAddres("서울 강남구 신사동 588-10"));
//		System.out.println(JosuChangeApi.getNewAddres("서울 강남구 신사동  "));
	}
}
