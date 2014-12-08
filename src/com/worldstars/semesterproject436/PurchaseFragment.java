package com.worldstars.semesterproject436;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PurchaseFragment extends ListFragment {
	private static final String FILE_NAME = "PurchasesList.txt";
	boolean itemClicked;
	
	public PurchaseFragment() {
		this.itemClicked = false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.pAdapter = new PurchaseAdapter(getActivity());
		setListAdapter(MainActivity.pAdapter);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceBundle) {
		super.onActivityCreated(savedInstanceBundle);

		ListView list = getListView();
		final SwipeDetector swipeDetector = new SwipeDetector();
		list.setOnTouchListener(swipeDetector);
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Purchase p = (Purchase) MainActivity.pAdapter.getItem(position);


				if (swipeDetector.getAction().equals(SwipeDetector.Action.RL)){
					// do the onSwipe action
					//MainActivity.pAdapter.clear();
					MainActivity.pAdapter.enableRemove(p);
					Toast.makeText(getActivity().getApplicationContext(), "swiped right-left!", Toast.LENGTH_LONG).show();
				} else if (swipeDetector.getAction().equals(SwipeDetector.Action.LR)) {
					MainActivity.pAdapter.disableRemove(p);
					Toast.makeText(getActivity().getApplicationContext(), "swiped left-right!", Toast.LENGTH_LONG).show();
				} else {
					// do the onItemClick action
					Toast.makeText(getActivity().getApplicationContext(), "short item click!", Toast.LENGTH_LONG).show();
					
					/*itemClicked = true;
					Bundle bundle = new Bundle();
					bundle.putInt("Icon", p.getIcon());
					bundle.putString("Cat",p.getCategory());
					bundle.putString("Subcat", p.getSubcategory());
					bundle.putString("Name", p.getName());
					bundle.putString("Cost", p.getCost());
					
					MainActivity.detailFrag = new DetailFragment();
					MainActivity.detailFrag.setArguments(bundle);*/
					if(p.itemClicked() == false){
						MainActivity.pAdapter.setClicked(p);
					}else{
						MainActivity.pAdapter.setUnclicked(p);
					}
					
				}
				
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,int position, long id) {
				Purchase p = (Purchase) MainActivity.pAdapter.getItem(position);
				if (swipeDetector.getAction().equals(SwipeDetector.Action.RL)){
					MainActivity.pAdapter.enableRemove(p);
					Toast.makeText(getActivity().getBaseContext(), "long swiped right-left!", Toast.LENGTH_LONG).show();
				}  else if (swipeDetector.getAction().equals(SwipeDetector.Action.LR)) {
					MainActivity.pAdapter.disableRemove(p);
					Toast.makeText(getActivity().getBaseContext(), "long swiped left-right!", Toast.LENGTH_LONG).show();
				} else {
					// do the onItemLongClick action
					Toast.makeText(getActivity().getBaseContext(), "long item click!!", Toast.LENGTH_LONG).show();

					/*itemClicked = true;
					Bundle bundle = new Bundle();
					bundle.putInt("Icon", p.getIcon());
					bundle.putString("Cat",p.getCategory());
					bundle.putString("Subcat", p.getSubcategory());
					bundle.putString("Name", p.getName());
					bundle.putString("Cost", p.getCost());
					
					MainActivity.detailFrag = new DetailFragment();
					MainActivity.detailFrag.setArguments(bundle);

					getActivity().getSupportFragmentManager().beginTransaction().remove(MainActivity.purchaseFrag).commit();
					getActivity().getFragmentManager().beginTransaction().replace(R.id.activity_main, MainActivity.detailFrag).commit();*/
					if(p.itemClicked() == false){
						MainActivity.pAdapter.setClicked(p);
					}else{
						MainActivity.pAdapter.setUnclicked(p);
					}
				}

				return true;
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();

		if (MainActivity.pAdapter.getCount() == 0) {
			loadItems();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		saveItems();
	}

	private void loadItems() {
		BufferedReader reader = null;

		try {
			FileInputStream inputStream = getActivity().openFileInput(FILE_NAME);
			reader = new BufferedReader(new InputStreamReader(inputStream));

			String name = null;
			String cost = null;
			String category = null;
			String subcategory = null;
			String icon = null;

			while ((name = reader.readLine()) != null) {
				cost = reader.readLine();
				category = reader.readLine();
				subcategory = reader.readLine();
				icon = reader.readLine();
				MainActivity.pAdapter.add(new Purchase(name, cost, category, subcategory, Integer.parseInt(icon)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void saveItems() {
		PrintWriter pWriter = null;

		try {
			FileOutputStream outputStream = getActivity().openFileOutput(FILE_NAME, MainActivity.MODE_PRIVATE);
			pWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));

			for (int i = 0; i < MainActivity.pAdapter.getCount(); i++) {
				pWriter.println(MainActivity.pAdapter.getItem(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.close();
			}
		}
	}


}