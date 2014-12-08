package com.worldstars.semesterproject436;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//Adding comment for git test
// Jazmyn Comment
public class MainActivity extends FragmentActivity implements ActionBar.TabListener{
	public static final String TAG = "Semester Project";

	static PurchaseFragment purchaseFrag;
	private SettingsFragment settingsFrag;
	static DetailFragment detailFrag;

	private static final String CATEGORY_FOOD = "Food";
	private static final String CATEGORY_ENTERTAINMENT = "Entertainment";
	private static final String CATEGORY_ELECTRONICS = "Electronics";
	private static final String CATEGORY_CLOTHES = "Clothes";

	private int current;
	private EditText mEditText;
	static PurchaseAdapter pAdapter;
	
	Tab purchaseTab;
	boolean cancel_clicked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		purchaseFrag = new PurchaseFragment();
		settingsFrag =  new SettingsFragment();

		//We are on the first tab
		current = 0;

		// Asking for the default ActionBar element that our platform supports.
		final ActionBar actionBar = getActionBar();

		// Screen handling while hiding ActionBar icon.
		//actionBar.setDisplayShowHomeEnabled(false);

		// Screen handling while hiding Actionbar title.
		//actionBar.setDisplayShowTitleEnabled(false);

		getWindow().getDecorView().setBackgroundResource(R.drawable.schooltheme); //change layout to default

		// Creating ActionBar tabs.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		purchaseTab = actionBar.newTab();

		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(purchaseTab.setIcon(R.drawable.listicon).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.plussign).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.settings).setTabListener(this));
		
		cancel_clicked = false;

	} 

	public void updateNameButton(View view) {

		mEditText = (EditText) findViewById(R.id.settings_editText);

		// Get a reference to the EditText field
		// Save user provided input from the EditText field
		String diary_name = mEditText.getText().toString();	
		getActionBar().setTitle(diary_name);

	}


	public void pushCork(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.corkie);

	}

	//Change Theme of the app
	public void pushDefault(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.schooltheme);
	}

	public void pushFish(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.aquariumtheme);
	}



	public void pushGreen(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.greenpolka);

	}

	public void pushIcey(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.eskimotheme);
	}


	public void pushCrayon(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.crayons);
	}


	public void pushRed(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.board);
	}

	public void pushSea(View view) {
		getWindow().getDecorView().setBackgroundResource(R.drawable.sea);
	}


	/*@Override
	public void onBackPressed() {

	    if (purchaseFrag.itemClicked == true) { 
	    	getFragmentManager().beginTransaction().remove(detailFrag).commit();
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, purchaseFrag).commit();
			purchaseFrag.itemClicked = false;
	    }else{
	    	super.onBackPressed();
	    }
	}*/


	@Override
	protected void onResume() {
		super.onResume();
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
			if(cancel_clicked == false){
				getFragmentManager().beginTransaction().remove(detailFrag).commit(); 
			}else{
				getSupportFragmentManager().beginTransaction().hide(purchaseFrag).commit(); 
			}
		}else{
			getFragmentManager().beginTransaction().remove(settingsFrag).commit(); 
		}
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {   	
		if (tab.getPosition() == 0) {   	 
			current = 0; 	
			if(cancel_clicked==false){
				getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, purchaseFrag).commit();
			}else{
				getSupportFragmentManager().beginTransaction().show(purchaseFrag).commit(); 
				cancel_clicked = false;
			}
			
		}else if (tab.getPosition() == 1) {
			current = 1;		
    		getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,purchaseFrag).commit();
			itemDialog().show();
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
				
				cancel_clicked = true;
				//getSupportFragmentManager().beginTransaction().remove(purchaseFrag).commit(); 
				//getActionBar().setSelectedNavigationItem(0);
				//getSupportFragmentManager().beginTransaction().attach(purchaseFrag).commit();
				
			}
		});

		builder.setNegativeButton("Add Item", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.i(TAG, "added new item");
				String itemName = ((EditText) v.findViewById(R.id.box1)).getText().toString();
				String itemPrice = ((EditText) v.findViewById(R.id.box2)).getText().toString();
				String itemCategory = ((Spinner) v.findViewById(R.id.category_spinner)).getSelectedItem().toString();
				String itemSubcategory = ((Spinner) v.findViewById(R.id.subcategory_spinner)).getSelectedItem().toString();
				int image;

				System.out.println(itemCategory);
				if(itemCategory.compareTo(CATEGORY_ENTERTAINMENT)==0){
					image = R.drawable.entertainment;
				}else if(itemCategory.compareTo(CATEGORY_CLOTHES) == 0){
					image = R.drawable.clothes;
				}else if(itemCategory.compareTo(CATEGORY_FOOD)==0){
					image = R.drawable.food;
				}else{
					image = R.drawable.electronics;
				}

				Pattern validPricing = Pattern.compile("(^([0-9]*).([0-9][0-9])$)|(^[1-9]+[0-9]*$)");
				Matcher pricingMatcher = validPricing.matcher(itemPrice);

				if (pricingMatcher.find()) {
					Pattern justDollars = Pattern.compile("^[1-9]+[0-9]*$");
					Matcher dollarsMatcher = justDollars.matcher(itemPrice);

					Purchase p;
					if (dollarsMatcher.find()) {
						p = new Purchase(itemName, itemPrice + ".00", itemCategory, itemSubcategory, image);
						pAdapter.add(p);
					} else {
						p = new Purchase(itemName, itemPrice, itemCategory, itemSubcategory, image);
						pAdapter.add(p);
					}
					
					Bundle bundle = new Bundle();
					bundle.putInt("Icon", p.getIcon());
					bundle.putString("Cat", p.getCategory());
					bundle.putString("Subcat", p.getSubcategory());
					bundle.putString("Name", p.getName());
					bundle.putString("Cost", p.getCost());

					
					detailFrag = new DetailFragment();
					detailFrag.setArguments(bundle);
					
					getSupportFragmentManager().beginTransaction().remove(purchaseFrag).commit(); 
					getFragmentManager().beginTransaction().replace(R.id.activity_main, detailFrag).commit();
				} else {
					Toast.makeText(getApplicationContext(), "Please enter a valid price! (#.##)", Toast.LENGTH_LONG).show();
					itemDialog().show();
				}
			}
		});

		return builder.create();
	}

	public AlertDialog deleteDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		final View v = getLayoutInflater().inflate(R.layout.remove_purchases, null);
		//TextView prompt = (TextView) v.findViewById(R.id.RemovePrompt);

		/*	if (pAdapter.oneItemIsSelected()) {
			prompt.setText("Are you sure that you would like to remove the selected purchases?");
		} else {
			prompt.setText("Are you sure that you would like to remove all selected purchases?");
		}*/
		//prompt.setText("SET TEXT TEST");
		builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.i(TAG, "canceled");
			}
		});

		builder.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Log.i(TAG, "Removing all selected items...");
				pAdapter.deleteAllSelected();
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
		} else if (id == R.id.delete_all) {
			pAdapter.clear();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void hackyTest(View v) {
		deleteDialog().show();
	}
}
