package com.worldstars.semesterproject436;

public class Purchase {
	public static final String ITEM_SEP = System.getProperty("line.separator");
	
	private String name;
	private String cost;
	private String category;
	private String subcategory;
	
	public Purchase() {
		name = "n/a";
		cost = "n/a";
		category = "n/a";
		subcategory = "n/a";
	}
	
	public Purchase(String name, String cost, String category, String subcategory) {
		this.name = name;
		this.cost = cost;
		this.category = category;
		this.subcategory = subcategory;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setCost(String cost) {
		this.cost = cost;
	}
	
	public String getCost() {
		return this.cost;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	public String getSubcategory() {
		return this.subcategory;
	}
	
	public String toString() {
		return name + ITEM_SEP + cost + ITEM_SEP + 
				category + ITEM_SEP + subcategory;
	}
}
