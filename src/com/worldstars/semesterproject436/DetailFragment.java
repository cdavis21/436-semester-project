package com.worldstars.semesterproject436;

import java.text.NumberFormat;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFragment extends Fragment{
	private String name;
	private String cost;
	private String category;
	private String subcategory;
	private int icon;
	public static final String TAG = "Semester Project";
	

	public DetailFragment(){

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		View view = inflater.inflate(R.layout.activity_detail, container, false);
		System.out.println("tester");
		System.out.println(getArguments().getString("Name"));

		Purchase p = new Purchase(getArguments().getString("Name"),
				getArguments().getString("Cost"), getArguments().getString("Cat"), 
				getArguments().getString("Subcat") , getArguments().getInt("Icon"));

		TextView tvName = (TextView) view.findViewById(R.id.NameView);
		tvName.setText(p.getName());

		TextView tvCost = (TextView) view.findViewById(R.id.CostView);
		tvCost.setText(p.getCost());

		TextView tvcategory = (TextView) view.findViewById(R.id.CategoryView);
		tvcategory.setText(p.getCategory());

		TextView tvsubcategory = (TextView) view.findViewById(R.id.SubcategoryView);
		tvsubcategory.setText(p.getSubcategory());



		TextView tvChina = (TextView) view.findViewById(R.id.ChinaView);
		//tvChina.setText(nf.format((p.calculateChina(p.getCost()))));

		TextView tvBrazil = (TextView) view.findViewById(R.id.BrazilView);
		//tvBrazil.setText(nf.format((p.calculateBrazil(p.getCost()))));

		TextView tvIndia = (TextView) view.findViewById(R.id.IndiaView);
		//tvIndia.setText(nf.format((p.calculateIndia(p.getCost()))));

		TextView tvIndonesia = (TextView) view.findViewById(R.id.IndonesiaView);
		//tvIndonesia.setText(nf.format((p.calculateIndonesia(p.getCost()))));

		ImageView ivPitcha = (ImageView) view.findViewById(R.id.imageView0);
		ivPitcha.setImageResource(p.getIcon());

		TextView tvPakistan = (TextView) view.findViewById(R.id.PakistanView);
		//tvPakistan.setText(nf.format((p.calculatePakistan(p.getCost()))));
		
		//Brazil
		if(!MainActivity.enabledBrazil) {
			tvBrazil.setText("Disabled");
		} else {
			tvBrazil.setText(nf.format((p.calculateBrazil(p.getCost()))));
		}
		
		//India
		if(!MainActivity.enabledChina) {
			tvChina.setText("Disabled");
		} else {
			tvChina.setText(nf.format((p.calculateChina(p.getCost()))));
		}
		
		if(!MainActivity.enabledIndia) {
			tvIndia.setText("Disabled");
		} else {
			tvIndia.setText(nf.format((p.calculateIndia(p.getCost()))));
		}
		
		if(!MainActivity.enabledIndonesia) {
			tvIndonesia.setText("Disabled");
		} else {
			tvIndonesia.setText(nf.format((p.calculateIndonesia(p.getCost()))));
		}
		
		if(!MainActivity.enabledPakistan) {
			tvPakistan.setText("Disabled");
		} else {
			tvPakistan.setText(nf.format((p.calculatePakistan(p.getCost()))));
		}
		
		
		

		return view;
	}
	
}
