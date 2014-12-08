package com.worldstars.semesterproject436;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		
		Intent intent = getIntent();
		Purchase p = new Purchase(intent);
		
		TextView tvName = (TextView) findViewById(R.id.NameView);
		tvName.setText(p.getName());

		TextView tvCost = (TextView) findViewById(R.id.CostView);
		tvCost.setText(p.getCost());
		
		TextView tvcategory = (TextView) findViewById(R.id.CategoryView);
		tvcategory.setText(p.getCategory());
		
		TextView tvsubcategory = (TextView) findViewById(R.id.SubcategoryView);
		tvsubcategory.setText(p.getSubcategory());
		
		
		
		TextView tvChina = (TextView) findViewById(R.id.ChinaView);
		tvChina.setText(nf.format((p.calculateChina(p.getCost()))));

		TextView tvBrazil = (TextView) findViewById(R.id.BrazilView);
		tvBrazil.setText(nf.format((p.calculateBrazil(p.getCost()))));

		
		TextView tvIndia = (TextView) findViewById(R.id.IndiaView);
		tvIndia.setText(nf.format((p.calculateIndia(p.getCost()))));

		
		TextView tvIndonesia = (TextView) findViewById(R.id.IndonesiaView);
		tvIndonesia.setText(nf.format((p.calculateIndonesia(p.getCost()))));

		
		TextView tvPakistan = (TextView) findViewById(R.id.PakistanView);
		tvPakistan.setText(nf.format((p.calculatePakistan(p.getCost()))));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
