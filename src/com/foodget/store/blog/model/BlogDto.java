
package com.foodget.store.blog.model;


public class BlogDto {
	@Override
	public String toString() {
		return "BlogDto [url=" + url + ", store_addres=" + store_addres + ", blog_number=" + blog_number
				+ ", store_name=" + store_name + ", store_seq=" + store_seq + ", store_phone=" + store_phone
				+ ", old_address=" + old_address + ", new_address=" + new_address + ", searchWord=" + searchWord
				+ ", blogRankInfoDto=" + blogRankInfoDto.toString() + ", rank_score=" + rank_score + "]";
	}
	private String url;
	private String store_addres;
	private String blog_number;
	private String store_name;
	private int store_seq;
	private String store_phone;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String old_address;
	private String new_address;
	
	private String searchWord;
	private BlogRankInfoDto blogRankInfoDto;
	private int rank_score;
	
	public String getStore_phone() {
		return store_phone;
	}
	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}
	public int getStore_seq() {
		return store_seq;
	}
	public void setStore_seq(int store_seq) {
		this.store_seq = store_seq;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStore_addres() {
		return store_addres;
	}
	public void setStore_addres(String store_addres) {
		this.store_addres = store_addres;
	}
	public String getBlog_number() {
		return blog_number;
	}
	public void setBlog_number(String blog_number) {
		this.blog_number = blog_number;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getOld_address() {
		return old_address;
	}
	public void setOld_address(String old_address) {
		this.old_address = old_address;
	}
	public String getNew_address() {
		return new_address;
	}
	public void setNew_address(String new_address) {
		this.new_address = new_address;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public BlogRankInfoDto getBlogRankInfoDto() {
		return blogRankInfoDto;
	}
	public void setBlogRankInfoDto(BlogRankInfoDto blogRankInfoDto) {
		this.blogRankInfoDto = blogRankInfoDto;
	}
	public int getRank_score() {
		return rank_score;
	}
	public void setRank_score(int rank_score) {
		this.rank_score = rank_score;
	}
	

}
