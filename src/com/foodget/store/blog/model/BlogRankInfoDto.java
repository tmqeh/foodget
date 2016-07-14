package com.foodget.store.blog.model;

import java.util.List;

public class BlogRankInfoDto extends BlogDto{
	private String blog_writer_id;
	private int comment_count;
	private int body_lenth;
	private int image_count;
	BlogImgInfoDto blogImgInfoDto;
	
	public String getBlog_writer_id() {
		return blog_writer_id;
	}
	@Override
	public String toString() {
		return " [url : "+getUrl()+"]BlogRankInfoDto [blog_writer_id=" + blog_writer_id + ","
				+ " comment_count=" + comment_count
				+ ", body_lenth=" + body_lenth + ", image_count=" + image_count + "]";
	}
	public void setBlog_writer_id(String blog_writer_id) {
		this.blog_writer_id = blog_writer_id;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public int getBody_lenth() {
		return body_lenth;
	}
	public void setBody_lenth(int body_lenth) {
		this.body_lenth = body_lenth;
	}
	public int getImage_count() {
		return image_count;
	}
	public void setImage_count(int image_count) {
		this.image_count = image_count;
	}
	public BlogImgInfoDto getBlogImgInfoDto() {
		return blogImgInfoDto;
	}
	public void setBlogImgInfoDto(BlogImgInfoDto blogImgInfoDto) {
		this.blogImgInfoDto = blogImgInfoDto;
	}
	
	
}
