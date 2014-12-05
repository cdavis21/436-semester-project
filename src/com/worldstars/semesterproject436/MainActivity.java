package com.worldstars.semesterproject436;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//Adding comment for git test
// Jazmyn Comment
public class MainActivity extends FragmentActivity implements ActionBar.TabListener{
	public static final String TAG = "Semester Project";
	
	private PurchaseFragment purchaseFrag;
	private SettingsFragment settingsFrag;
	private AddNewFragment addFrag;
	
	private static final String CATEGORY_FOOD = "Food";
	private static final String CATEGORY_ENTERTAINMENT = "Entertainment";
	private static final String CATEGORY_ELECTRONICS = "Electronics";
	private static final String CATEGORY_CLOTHES = "Clothes";
	
	private int current;
	
	static PurchaseAdapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        purchaseFrag = new PurchaseFragment();
        settingsFrag =  new SettingsFragment();
        addFrag = new AddNewFragment();
        
        //We are on the first tab
        current = 0;
        
        // Asking for the default ActionBar element that our platform supports.
        final ActionBar actionBar = getActionBar();
        
                // Screen handling while hiding ActionBar icon.
                //actionBar.setDisplayShowHomeEnabled(false);

                // Screen handling while hiding Actionbar title.
                //actionBar.setDisplayShowTitleEnabled(false);

         // Creating ActionBar tabs.
         actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
         
         // For each of the sections in the app, add a tab to the action bar.
         actionBar.addTab(actionBar.newTab().setIcon(R.drawable.listicon).setTabListener(this));
         actionBar.addTab(actionBar.newTab().setIcon(R.drawable.plussign).setTabListener(this));
         actionBar.addTab(actionBar.newTab().setIcon(R.drawable.settings).setTabListener(this));
         
    } 
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(TAG)) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(TAG));
        }
    }
 
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(TAG, getActionBar().getSelectedNavigationIndex());
    }
 
   
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    	if(current == 0){
    		getSupportFragmentManager().beginTransaction().remove(purchaseFrag).commit(); 		
    	}else if (current == 1){
    		getFragmentManager().beginTransaction().remove(addFrag).commit(); 		
    	}else{
    		getFragmentManager().beginTransaction().remove(settingsFrag).commit(); 
    	}
    }
 
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {   	
    	if (tab.getPosition() == 0) {   	 
    		current = 0;   	 
    		getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, purchaseFrag).commit();
    	}else if (tab.getPosition() == 1) {
    		current = 1;  	 
    		getFragmentManager().beginTransaction().replace(R.id.activity_main, addFrag).commit();
    	 	//itemDialog().show();
    	}else{
    		current = 2;
    		getFragmentManager().beginTransaction().replace(R.id.activity_main, settingsFrag).commit();
    	}
    }
 
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

	public AlertDialog itemDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View v = getLayoutInflater().inflate(R.layout.add_item_dialog, null);
		
		final Spinner category = (Spinner) v.findViewById(R.id.category_spinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
		        R.array.categories_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		category.setAdapter(categoryAdapter);
		
		final Spinner subcategory = (Spinner) v.findViewById(R.id.subcategory_spinner);
		category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String item = parent.getItemAtPosition(position).toString();
				
				if (item.equals(CATEGORY_FOOD)) {
					// Create an ArrayAdapter using the string array and a default spinner layout
					ArrayAdapter<CharSequence> subcategoryAdapter = ArrayAdapter.createFromResource(getBaseContext(),
					        R.array.subcategory_food, android.R.layout.simple_spinner_item);
					// Specify the layout to use when the list of choices appears
					subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// Apply the adapter to the spinner
					subcategory.setAdapter(subcategoryAdapter);
				} else if (item.equals(CATEGORY_CLOTHES)) {
					// Create an ArrayAdapter using the string array and a default spinner layout
					ArrayAdapter<CharSequence> subcategoryAdapter = ArrayAdapter.createFromResource(getBaseContext(),
					        R.array.subcategory_clothes, android.R.layout.simple_spinner_item);
					// Specify the layout to use when the list of choices appears
					subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// Apply the adapter to the spinner
					subcategory.setAdapter(subcategoryAdapter);
				} else if (item.equals(CATEGORY_ELECTRONICS)) {
					// Create an ArrayAdapter using the string array and a default spinner layout
					ArrayAdapter<CharSequence> subcategoryAdapter = ArrayAdapter.createFromResource(getBaseContext(),
					        R.array.subcategory_electronics, android.R.layout.simple_spinner_item);
					// Specify the layout to use when the list of choices appears
					subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// Apply the adapter to the spinner
					subcategory.setAdapter(subcategoryAdapter);
				} else if (item.equals(CATEGORY_ENTERTAINMENT)) {
					// Create an ArrayAdapter using the string array and a default spinner layout
					ArrayAdapter<CharSequence> subcategoryAdapter = ArrayAdapter.createFromResource(getBaseContext(),
					        R.array.subcategory_entertainment, android.R.layout.simple_spinner_item);
					// Specify the layout to use when the list of choices appears
					subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					// Apply the adapter to the spinner
					subcategory.setAdapter(subcategoryAdapter);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// swag
			}
		});
		
		builder.setView(v);
		builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.i(TAG, "canceled");
			}
		});
		
		builder.setNegativeButton("Add Item", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.i(TAG, "added new item");
				String box1 = " " + ((EditText) v.findViewById(R.id.box1)).getText().toString();
				String box2 = " " + ((EditText) v.findViewById(R.id.box2)).getText().toString();
				String box3 = " " + ((Spinner) v.findViewById(R.id.category_spinner)).getSelectedItem().toString();
				String box4 = " " + ((Spinner) v.findViewById(R.id.subcategory_spinner)).getSelectedItem().toString();
				int image;
				
				System.out.println(box3);
				if(box3.substring(1).compareTo(CATEGORY_ENTERTAINMENT)==0){
					image = R.drawable.entertainment;
				}else if(box3.substring(1).compareTo(CATEGORY_CLOTHES) == 0){
					image = R.drawable.clothes;
				}else if(box3.substring(1).compareTo(CATEGORY_FOOD)==0){
					image = R.drawable.food;
				}else{
					image = R.drawable.electronics;
				}
				pAdapter.add(new Purchase(box1, box2, box3, box4, image));
			}
		});
		
		return builder.create();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	itemDialog().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
