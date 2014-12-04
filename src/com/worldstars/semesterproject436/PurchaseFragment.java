package com.worldstars.semesterproject436;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class PurchaseFragment extends ListFragment {

	 
	  
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
}
