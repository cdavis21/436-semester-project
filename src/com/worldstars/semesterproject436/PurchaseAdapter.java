package com.worldstars.semesterproject436;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class PurchaseAdapter extends BaseAdapter {
	private final List<Purchase> purchases = new ArrayList<Purchase>();
	private final Context context;
	
	public PurchaseAdapter(Context context) {
		this.context = context;
	}
	
	public void add(Purchase p) {
		purchases.add(p);
		notifyDataSetChanged();
	}
	
	public void clear() {
		purchases.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return purchases.size();
	}
	
	@Override
	public Object getItem(int pos) {
		return purchases.get(pos);
	}
	
	@Override
	public long getItemId(int pos) {
		return pos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Purchase currentPurchase = (Purchase) getItem(position);
		RelativeLayout purchaseLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.purchase, null);
		
		final TextView nameView = (TextView) purchaseLayout.findViewById(R.id.NameView);
		nameView.setText(currentPurchase.getName());
		
		final TextView costView = (TextView) purchaseLayout.findViewById(R.id.CostView);
		costView.setText(currentPurchase.getCost());
		
		final TextView categoryView = (TextView) purchaseLayout.findViewById(R.id.CategoryView);
		categoryView.setText(currentPurchase.getCategory());
		
		final TextView subcategoryView = (TextView) purchaseLayout.findViewById(R.id.SubcategoryView);
		subcategoryView.setText(currentPurchase.getSubcategory());
		return purchaseLayout;
	}
}
