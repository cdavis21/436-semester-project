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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
//Adding comment for git test
// Jazmyn Comment
public class MainActivity extends FragmentActivity implements ActionBar.TabListener{
	public static final String TAG = "Semester Project";
	private final String FILE_SETTINGS = "UserSettings.txt";
	
	public enum SelectedTheme {
		THEME_GRAPH, THEME_FISH, THEME_CRAYONS,
		THEME_OCEAN, THEME_ICE, THEME_BBOARD, 
		THEME_PAPER, THEME_CORK
	}

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

	// Variables for storing user settings
	private static String USER_NAME = "Cost of Living Journal";
	private SelectedTheme userTheme = SelectedTheme.THEME_GRAPH;
	
	public static boolean enabledBrazil = true;
	public static boolean enabledChina = true;
	public static boolean enabledIndia = true;
	public static boolean enabledIndonesia = true;
	public static boolean enabledPakistan = true;
	
	
	private SharedPreferences pref;
	
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

		//getWindow().getDecorView().setBackgroundResource(R.drawable.schooltheme); //change layout to default
		
		// Creating ActionBar tabs.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		purchaseTab = actionBar.newTab();

		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(purchaseTab.setIcon(R.drawable.listicon).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.plussign).setTabListener(this));
		actionBar.addTab(actionBar.newTab().setIcon(R.drawable.settings).setTabListener(this));
		
		//pref = PreferenceManager.getDefaultSharedPreferences(this);
	/*
		SharedPreferences.Editor editor = pref.edit();
		 editor.putString("Brazil_Status","true");
		 editor.putString("China_Status","true");
		 editor.putString("India_Status","true");
		 editor.putString("Indonesia_Status","true");
		 editor.putString("Pakistan_Status","true");
		 editor.apply();
		*/
		cancel_clicked = false;
	} 
	
	public void selectBrazil(View v) {
		if (enabledBrazil) {
			enabledBrazil = false;
		} else {
			enabledBrazil = true;
		}
		renderSettings();
	}
	
	public void selectChina(View v) {
		if (enabledChina) {
			enabledChina = false;
		} else {
			enabledChina = true;
		}
		renderSettings();
	}
	public void selectIndia(View v) {
		if (enabledIndia) {
			enabledIndia = false;
		} else {
			enabledIndia = true;
		}
		renderSettings();
	}
	
	public void selectIndonesia(View v) {
		if (enabledIndonesia) {
			enabledIndonesia = false;
		} else {
			enabledIndonesia = true;
		}
		renderSettings();
	}
	
	public void selectPakistan(View v) {
		if (enabledPakistan) {
			enabledPakistan = false;
		} else {
			enabledPakistan = true;
		}
		renderSettings();
	}

	public void updateNameButton(View view) {

		mEditText = (EditText) findViewById(R.id.settings_editText);

		// Get a reference to the EditText field
		// Save user provided input from the EditText field
		String diary_name = mEditText.getText().toString();
		USER_NAME = diary_name;
		//getActionBar().setTitle(diary_name);
		saveSettings();
		renderSettings();
	}


	public void pushCork(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.corkie);
		userTheme = SelectedTheme.THEME_CORK;
		saveSettings();
		renderSettings();
	}

	//Change Theme of the app
	public void pushDefault(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.schooltheme);
		userTheme = SelectedTheme.THEME_GRAPH;
		saveSettings();
		renderSettings();
	}

	public void pushFish(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.aquariumtheme);
		userTheme = SelectedTheme.THEME_FISH;
		saveSettings();
		renderSettings();
	}



	public void pushGreen(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.greenpolka);
		userTheme = SelectedTheme.THEME_PAPER;
		saveSettings();
		renderSettings();
	}

	public void pushIcey(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.eskimotheme);
		userTheme = SelectedTheme.THEME_ICE;
		saveSettings();
		renderSettings();
	}


	public void pushCrayon(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.crayons);
		userTheme = SelectedTheme.THEME_CRAYONS;
		saveSettings();
		renderSettings();
	}


	public void pushRed(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.board);
		userTheme = SelectedTheme.THEME_BBOARD;
		saveSettings();
		renderSettings();
	}

	public void pushSea(View view) {
		//getWindow().getDecorView().setBackgroundResource(R.drawable.sea);
		userTheme = SelectedTheme.THEME_BBOARD;
		saveSettings();
		renderSettings();
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
		loadSettings();
		renderSettings();
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
			if(tab.getPosition()==1){
				//Detach this view to attach on different view
				getSupportFragmentManager().beginTransaction().detach(purchaseFrag).commit(); 
			}else if(tab.getPosition() == 2){
				getSupportFragmentManager().beginTransaction().remove(purchaseFrag).commit(); 
			}
			
		}else if (current == 1){
			if(cancel_clicked == false){
				getFragmentManager().beginTransaction().remove(detailFrag).commit(); 
			}else{
				getSupportFragmentManager().beginTransaction().detach(purchaseFrag).commit(); 
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
				getSupportFragmentManager().beginTransaction().attach(purchaseFrag).commit(); 
				cancel_clicked = false;
			}
			
		}else if (tab.getPosition() == 1) {
			current = 1;		
    		getSupportFragmentManager().beginTransaction().attach(purchaseFrag).commit();
			itemDialog().show();
		}else{
			current = 2;
			getFragmentManager().beginTransaction().replace(R.id.activity_main, settingsFrag).commit();
		}
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		if(tab.getPosition()==1){
			current =1;
			getFragmentManager().beginTransaction().remove(detailFrag).commit();
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, purchaseFrag).commit();
			itemDialog().show();
		}
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
				getActionBar().selectTab(purchaseTab);
				
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
		builder.setView(v);
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
		if (id == R.id.temp) {
			loadSettings();
			renderSettings();
		} else if (id == R.id.action_settings) {
			itemDialog().show();
			return true;
		} else if (id == R.id.delete_all) {
			pAdapter.clear();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void loadSettings() {
		BufferedReader reader = null;
		
		try {
			FileInputStream inputStream = openFileInput(FILE_SETTINGS);
			reader = new BufferedReader(new InputStreamReader(inputStream));
			
			USER_NAME = reader.readLine();
			userTheme = SelectedTheme.valueOf(reader.readLine());
			enabledBrazil = Boolean.valueOf(reader.readLine());
			Log.i(TAG, "" + enabledBrazil);
			enabledChina = Boolean.valueOf(reader.readLine());
			enabledIndia = Boolean.valueOf(reader.readLine());
			enabledIndonesia = Boolean.valueOf(reader.readLine());
			enabledPakistan = Boolean.valueOf(reader.readLine());
			/*while ((USER_NAME = reader.readLine()) != null) {
				String s = reader.readLine();
				Log.i(TAG, s);
				userTheme = SelectedTheme.valueOf(s);
				//Log.i(TAG, reader.readLine());
				
				//userTheme = SelectedTheme.valueOf(reader.readLine());
				/*enabledBrazil = Boolean.valueOf(reader.readLine());
				enabledChina = Boolean.valueOf(reader.readLine());
				enabledIndia = Boolean.valueOf(reader.readLine());
				enabledIndonesia = Boolean.valueOf(reader.readLine());
				enabledPakistan = Boolean.valueOf(reader.readLine());
				
			}*/
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
	
	private void saveSettings() {
		PrintWriter pWriter = null;
		
		try {
			FileOutputStream outputStream = openFileOutput(FILE_SETTINGS, MODE_PRIVATE);
			pWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
			
			pWriter.println(USER_NAME);
			pWriter.println(userTheme.toString());
			pWriter.println(enabledBrazil);
			pWriter.println(enabledChina);
			pWriter.println(enabledIndia);
			pWriter.println(enabledIndonesia);
			pWriter.println(enabledPakistan);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pWriter != null) {
				pWriter.close();
			}
		}
	}
	
	public void renderSettings() {
		getActionBar().setTitle(USER_NAME);
		
		switch(userTheme) {
		case THEME_GRAPH:
			getWindow().getDecorView().setBackgroundResource(R.drawable.schooltheme);
			break;
		case THEME_FISH:
			getWindow().getDecorView().setBackgroundResource(R.drawable.aquariumtheme);
			break;
		case THEME_CRAYONS:
			getWindow().getDecorView().setBackgroundResource(R.drawable.crayons);
			break;
		case THEME_OCEAN:
			getWindow().getDecorView().setBackgroundResource(R.drawable.sea);
			break;
		case THEME_ICE:
			getWindow().getDecorView().setBackgroundResource(R.drawable.eskimotheme);
			break;
		case THEME_BBOARD:
			getWindow().getDecorView().setBackgroundResource(R.drawable.board);
			break;
		case THEME_PAPER:
			getWindow().getDecorView().setBackgroundResource(R.drawable.greenpolka);
			break;
		case THEME_CORK:
			getWindow().getDecorView().setBackgroundResource(R.drawable.corkie);
			break;
		}
		
		final View v = getLayoutInflater().inflate(R.layout.settings, null);
		((CheckBox) v.findViewById(R.id.brazilCheckBox)).setChecked(enabledBrazil);
		((CheckBox) v.findViewById(R.id.chinaCheckBox)).setChecked(enabledChina);
		((CheckBox) v.findViewById(R.id.indiaCheckBox)).setChecked(enabledIndia);
		((CheckBox) v.findViewById(R.id.indonesiaCheckBox)).setChecked(enabledIndonesia);
		((CheckBox) v.findViewById(R.id.pakistanCheckBox)).setChecked(enabledPakistan);
	}
	
	public void hackyTest(View v) {
		deleteDialog().show();
	}
}
