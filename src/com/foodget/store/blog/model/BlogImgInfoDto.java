package com.foodget.store.blog.model;

import java.util.List;

public class BlogImgInfoDto extends BlogRankInfoDto{
	
	private List<String> imgSrcList;

	public List<String> getImgSrcList() {
		return imgSrcList;
	}

	public void setImgSrcList(List<String> imgSrcList) {
		this.imgSrcList = imgSrcList;
	}

	@Override
	public String toString() {
		return "BlogImgInfoDto [imgSrcList=" + imgSrcList + "]";
//		return "BlogImgInfoDto [imgSrcList=생략]";
	}
	
}
