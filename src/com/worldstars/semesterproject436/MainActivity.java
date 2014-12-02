package com.worldstars.semesterproject436;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
//Adding comment for git test
// Jazmyn Comment
public class MainActivity extends Activity {
	public static final String TAG = "Semester Project";
	
	public PurchaseAdapter pAdapter;
	
	ActionBar.Tab purchasesTab, addNewTab, filterTab;
        Fragment purchasesFragmentTab = new PurcahsesFragment();
        Fragment addNewFragmentTab = new AddNewFragment();
        //Fragment fordFragmentTab = new FordFragmentTab();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Asking for the default ActionBar element that our platform supports.
        ActionBar actionBar = getActionBar();
        
                // Screen handling while hiding ActionBar icon.
                //actionBar.setDisplayShowHomeEnabled(false);

                // Screen handling while hiding Actionbar title.
                //actionBar.setDisplayShowTitleEnabled(false);

         // Creating ActionBar tabs.
         actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
         
         // Setting custom tab icons.
         purchasesTab = actionBar.newTab().setText("Purchases");
         addNewTab = actionBar.newTab().setText("Add New Purchase");
               // fordTab = actionBar.newTab().setIcon(R.drawable.ford_logo);
                
         // Setting tab listeners.
         purchasesTab.setTabListener(new TabListener(purchasesFragmentTab));
         addNewTab.setTabListener(new TabListener(addNewFragmentTab));
               //fordTab.setTabListener(new TabListener(fordFragmentTab));
               
          // Adding tabs to the ActionBar.
          actionBar.addTab(purchasesTab);
          actionBar.addTab(addNewTab);
                //actionBar.addTab(fordTab);
          
  		pAdapter = new PurchaseAdapter(getApplicationContext());
  		ListView list = (ListView) findViewById(R.id.list);
  		list.setAdapter(pAdapter);
    }
    public class PurcahsesFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            //View rootView = inflater.inflate(R.layout.purchase, container, false);
            //return rootView;
        	return null;
        }
    }
    public class AddNewFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	itemDialog().show();
            //View rootView = inflater.inflate(R.layout.purchase, container, false);
            //return rootView;
        	return null;
        }
    }
	public AlertDialog itemDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		final View v = getLayoutInflater().inflate(R.layout.add_item_dialog, null);
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
				
				pAdapter.add(new Purchase(box1, box2));
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
