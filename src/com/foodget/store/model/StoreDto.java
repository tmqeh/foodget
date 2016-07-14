package com.foodget.store.model;

public class StoreDto {
	private String store_name;
	private String store_address;
	private String store_phone;
	private String store_food;
	private int hot_taste;
	private int sour_taste;
	private int clean_taste;
	private int sweet_taste;
	private int fresh_taste;
	private int k_hot_taste;
	private double store_logitude;
	
	public String getStore_food() {
		return store_food;
	}
	public void setStore_food(String store_food) {
		this.store_food = store_food;
	}
	public int getHot_taste() {
		return hot_taste;
	}
	public void setHot_taste(int hot_taste) {
		this.hot_taste = hot_taste;
	}
	public int getSour_taste() {
		return sour_taste;
	}
	public void setSour_taste(int sour_taste) {
		this.sour_taste = sour_taste;
	}
	public int getClean_taste() {
		return clean_taste;
	}
	public void setClean_taste(int clean_taste) {
		this.clean_taste = clean_taste;
	}
	public int getSweet_taste() {
		return sweet_taste;
	}
	public void setSweet_taste(int sweet_taste) {
		this.sweet_taste = sweet_taste;
	}
	public int getFresh_taste() {
		return fresh_taste;
	}
	public void setFresh_taste(int fresh_taste) {
		this.fresh_taste = fresh_taste;
	}
	public int getK_hot_taste() {
		return k_hot_taste;
	}
	public void setK_hot_taste(int k_hot_taste) {
		this.k_hot_taste = k_hot_taste;
	}
	public double getStore_logitude() {
		return store_logitude;
	}
	public void setStore_logitude(double store_logitude) {
		this.store_logitude = store_logitude;
	}
	public double getStore_latitude() {
		return store_latitude;
	}
	public void setStore_latitude(double store_latitude) {
		this.store_latitude = store_latitude;
	}
	private double store_latitude;

	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getStore_address() {
		return store_address;
	}
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}
	public String getStore_phone() {
		return store_phone;
	}
	public void setStore_phone(String store_phone) {
		this.store_phone = store_phone;
	}
	
	
	
}
