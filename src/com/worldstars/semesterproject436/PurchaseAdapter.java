package com.worldstars.semesterproject436;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
	
	static class ViewHolderPurchase {
		ImageView imageView;
		TextView nameView;
		TextView costView;
		TextView categoryView;
		TextView subcategoryView;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/*
		final Purchase currentPurchase = (Purchase) getItem(position);
		LinearLayout purchaseLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.purchase, null);
		
		final ImageView imageView = (ImageView) purchaseLayout.findViewById(R.id.imageView1);
		imageView.setImageResource(currentPurchase.getIcon());
		
		final TextView nameView = (TextView) purchaseLayout.findViewById(R.id.NameView);
		nameView.setText(currentPurchase.getName());
		
		final TextView costView = (TextView) purchaseLayout.findViewById(R.id.CostView);
		costView.setText(currentPurchase.getCost());
		
		final TextView categoryView = (TextView) purchaseLayout.findViewById(R.id.CategoryView);
		categoryView.setText(currentPurchase.getCategory());
		
		final TextView subcategoryView = (TextView) purchaseLayout.findViewById(R.id.SubcategoryView);
		subcategoryView.setText(currentPurchase.getSubcategory());
		return purchaseLayout;*/
		
		ViewHolderPurchase vhp;
		
		if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(R.layout.purchase, parent, false);
			
			vhp = new ViewHolderPurchase();
			vhp.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
			vhp.nameView = (TextView) convertView.findViewById(R.id.NameView);
			vhp.costView = (TextView) convertView.findViewById(R.id.CostView);
			vhp.categoryView = (TextView) convertView.findViewById(R.id.CategoryView);
			vhp.subcategoryView = (TextView) convertView.findViewById(R.id.SubcategoryView);
			
			convertView.setTag(vhp);
		} else {
			vhp = (ViewHolderPurchase) convertView.getTag();
		}
		
		Purchase currentPurchase = (Purchase) getItem(position);
		if (currentPurchase != null) {
			vhp.imageView.setImageResource(currentPurchase.getIcon());
			vhp.nameView.setText(currentPurchase.getName());
			vhp.costView.setText(currentPurchase.getCost());
			vhp.categoryView.setText(currentPurchase.getCategory());
			vhp.subcategoryView.setText(currentPurchase.getSubcategory());
		}
		
		return convertView;
	}
}
