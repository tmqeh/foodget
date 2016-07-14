package com.foodget.store.blog.model;

import java.util.List;

public class BlogRankInfoDto extends BlogDto{
	private String blogId;
	private int commentCount;
	private int lenthBody;
	private int imageCount;
	BlogImgInfoDto blogImgInfoDto;
	
	public BlogImgInfoDto getBlogImgInfoDto() {
		return blogImgInfoDto;
	}
	public void setBlogImgInfoDto(BlogImgInfoDto blogImgInfoDto) {
		this.blogImgInfoDto = blogImgInfoDto;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public int getLenthBody() {
		return lenthBody;
	}
	public void setLenthBody(int lenthBody) {
		this.lenthBody = lenthBody;
	}
	public int getImageCount() {
		return imageCount;
	}
	@Override
	public String toString() {
		return "BlogRankInfoDto [blogId=" + blogId + ", commentCount=" + commentCount + ", lenthBody=" + lenthBody
				+ ", imageCount=" + imageCount + ", blogImgInfoDto=" + blogImgInfoDto.toString() + "]";
	}
	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	
	
}
