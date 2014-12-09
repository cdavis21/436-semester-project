package com.worldstars.semesterproject436;

import android.content.Intent;

public class Purchase {
	public static final String ITEM_SEP = System.getProperty("line.separator");
	private boolean REMOVE_ENABLED = false;
	private boolean CLICKED = false;
	
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
	
	public Purchase(Intent intent){
		this.name = intent.getStringExtra("name");
		this.cost = intent.getStringExtra("cost");
		this.category = intent.getStringExtra("category");
		this.subcategory = intent.getStringExtra("subcategory");
		this.icon = intent.getIntExtra("icon", 0);
	}
	
	public Intent packageToIntent() {
		Intent intent = new Intent();
		intent.putExtra("name", name);
		intent.putExtra("cost", cost);
		intent.putExtra("category", category);
		intent.putExtra("subcategory", subcategory);
		intent.putExtra("icon", icon);
		return intent;
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
	
	public void setClicked(){
		CLICKED = true;
	}
	
	public void setUnclicked(){
		CLICKED = false;
	}
	
	public boolean itemClicked(){
		return CLICKED;
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
	
	public double calculateChina(String cost){
		Double numCost = Double.parseDouble(cost);
		
		return numCost* .6 ;
		
	}
	public double calculateBrazil(String cost){
		Double numCost = Double.parseDouble(cost);
	
		return numCost* .7 ;
		
	}
	public double calculateIndia(String cost){
		Double numCost = Double.parseDouble(cost);
		
		return numCost* .3 ;
		
	}public double calculateIndonesia(String cost){
		Double numCost = Double.parseDouble(cost);
		
		return numCost* .4 ;
		
	}public double calculatePakistan(String cost){
		Double numCost = Double.parseDouble(cost);
		
		return numCost* .3 ;
		
	}
	
	
}
