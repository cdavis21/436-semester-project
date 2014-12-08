package com.worldstars.semesterproject436;
import java.text.NumberFormat;
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
	private final List<Purchase> purchasesToDelete = new ArrayList<Purchase>();
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
	
	public void enableRemove(Purchase p) {
		p.enableRemove();
		purchasesToDelete.add(p);
		notifyDataSetChanged();
	}
	
	public void disableRemove(Purchase p) {
		p.disableRemove();
		purchasesToDelete.remove(p);
		notifyDataSetChanged();
	}
	
	public void setClicked(Purchase p){
		p.setClicked();
		notifyDataSetChanged();
	}
	
	public void setUnclicked(Purchase p){
		p.setUnclicked();
		notifyDataSetChanged();
	}
	
	public boolean oneItemIsSelected() {
		if (purchasesToDelete.size() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deleteAllSelected() {
		for (Purchase p: purchasesToDelete) {
			purchases.remove(p);
		}
		
		purchasesToDelete.clear();
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
	public int getViewTypeCount() {
		return 3;
	}
	
	@Override
	public int getItemViewType(int position) {
		Purchase p = (Purchase) getItem(position);
		if (p.removeIsEnabled()) {
			return 0;
		} else if(p.itemClicked()) {
			return 2;
		}else{
			return 1;
		}
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
		Purchase currentPurchase = (Purchase) getItem(position);
		
		if (convertView == null) {
			vhp = new ViewHolderPurchase();
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			
			if (getItemViewType(position) == 0) {
				convertView = inflater.inflate(R.layout.delete_purchase, parent, false);
			} else if (getItemViewType(position) == 1) {
				convertView = inflater.inflate(R.layout.purchase, parent, false);
			}else if (getItemViewType(position) == 2){
				convertView = inflater.inflate(R.layout.activity_detail, parent, false);
				
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				
				TextView tvChina = (TextView) convertView.findViewById(R.id.ChinaView);
				tvChina.setText(nf.format((currentPurchase.calculateChina(currentPurchase.getCost()))));

				TextView tvBrazil = (TextView) convertView.findViewById(R.id.BrazilView);
				tvBrazil.setText(nf.format((currentPurchase.calculateBrazil(currentPurchase.getCost()))));

				
				TextView tvIndia = (TextView) convertView.findViewById(R.id.IndiaView);
				tvIndia.setText(nf.format((currentPurchase.calculateIndia(currentPurchase.getCost()))));

				
				TextView tvIndonesia = (TextView) convertView.findViewById(R.id.IndonesiaView);
				tvIndonesia.setText(nf.format((currentPurchase.calculateIndonesia(currentPurchase.getCost()))));

				
				TextView tvPakistan = (TextView) convertView.findViewById(R.id.PakistanView);
				tvPakistan.setText(nf.format((currentPurchase.calculatePakistan(currentPurchase.getCost()))));
			}
			
			vhp.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
			vhp.nameView = (TextView) convertView.findViewById(R.id.NameView);
			vhp.costView = (TextView) convertView.findViewById(R.id.CostView);
			vhp.categoryView = (TextView) convertView.findViewById(R.id.CategoryView);
			vhp.subcategoryView = (TextView) convertView.findViewById(R.id.SubcategoryView);
			
			convertView.setTag(vhp);
		} else {
			vhp = (ViewHolderPurchase) convertView.getTag();
		}
		
		if (currentPurchase != null) {
			if (currentPurchase.removeIsEnabled()) {
				vhp.imageView.setImageResource(R.drawable.delete);
			} else {
				vhp.imageView.setImageResource(currentPurchase.getIcon());
			}
			vhp.nameView.setText(currentPurchase.getName());
			vhp.costView.setText(currentPurchase.getCost());
			vhp.categoryView.setText(currentPurchase.getCategory());
			vhp.subcategoryView.setText(currentPurchase.getSubcategory());
		}
		
		return convertView;
	}
}
