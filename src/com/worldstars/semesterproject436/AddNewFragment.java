package com.worldstars.semesterproject436;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddNewFragment extends Fragment {
	public AddNewFragment(){
		
	}
		  
		 @Override
		 public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		   
		 }
		  
		 @Override
		 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		  return inflater.inflate(R.layout.activity_detail, container, false);
		 }
}
