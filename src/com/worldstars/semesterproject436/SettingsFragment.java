package com.worldstars.semesterproject436;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {
	public SettingsFragment(){
		
	}
		  
		 @Override
		 public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		   
		 }
		  
		 @Override
		 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		  return inflater.inflate(R.layout.settings, container, false);
		 }
		  
}
