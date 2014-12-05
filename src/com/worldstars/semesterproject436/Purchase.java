package com.worldstars.semesterproject436;

public class Purchase {
	public static final String ITEM_SEP = System.getProperty("line.separator");
	private boolean REMOVE_ENABLED = false;
	
	private String name;
	private String cost;
	private String category;
	private String subcategory;
	private int icon;
	
	public Purchase() {
		name = "n/a";
		cost = "n/a";
		category = "n/a";
		subcategory = "n/a";
		icon = -1;
	}
	
	public Purchase(String name, String cost, String category, String subcategory, int icon) {
		this.name = name;
		this.cost = cost;
		this.category = category;
		this.subcategory = subcategory;
		this.icon = icon;
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
	
	public void setIcon(int drawable){
		this.icon = drawable;
	}
	
	public int getIcon(){
		return this.icon;
	}
	
	public void enableRemove() {
		REMOVE_ENABLED = true;
		System.out.println("Enabled remove for item with data:");
		System.out.println(this.toString());
	}
	
	public void disableRemove() {
		REMOVE_ENABLED = false;
	}
	
	public boolean removeIsEnabled() {
		return REMOVE_ENABLED;
	}
	
	public String toString() {
		return name + ITEM_SEP + cost + ITEM_SEP + 
				category + ITEM_SEP + subcategory + ITEM_SEP + icon;
	}
}
