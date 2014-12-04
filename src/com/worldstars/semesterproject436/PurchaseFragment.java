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

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class PurchaseFragment extends ListFragment {
	private static final String FILE_NAME = "PurchasesList.txt";
	 
	public PurchaseFragment() {
	 
	}
	  
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.pAdapter = new PurchaseAdapter(getActivity());
		setListAdapter(MainActivity.pAdapter);	 
	}
  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment, container, false);
	}
	  
	@Override
	public void onListItemClick(ListView list, View v, int position, long id) {
		Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
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
			
			while ((name = reader.readLine()) != null) {
				cost = reader.readLine();
				category = reader.readLine();
				subcategory = reader.readLine();
				MainActivity.pAdapter.add(new Purchase(name, cost, category, subcategory));
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