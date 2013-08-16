package com.example.livewell;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ManageDiet extends BaseActivity implements OnItemSelectedListener
{
	SharedPreferences settings;
	Spinner spinner;
	Button btnAdd, btnDelete;
	ArrayList<String> selectedFood;
	String[] foodListArray = {};
	ListView listView;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_diet);
		
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_APPEND);
		
		initTextBox(R.id.textview_BMR, SETTINGS_PREFS_BMR);
		initTextBox(R.id.textview_LWCalories, SETTINGS_PREFS_LW_CALORIES);
		initTextBox(R.id.textview_RemainingCalories, SETTINGS_PREFS_LW_CALORIES);
		
		spinner = (Spinner)findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(this);
		initSpinner();
		
		listView = (ListView)findViewById(R.id.listview_FoodList);
		selectedFood = new ArrayList<String>();
		
		btnAdd = (Button)findViewById(R.id.btn_add);
		btnDelete = (Button)findViewById(R.id.btn_delete);

		btnAdd.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
	        {
				String text = spinner.getSelectedItem().toString();
				selectedFood.add(text);
				handleListView();
	        }
		});
		
		
		btnDelete.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
	        {
	        	int foodId = spinner.getSelectedItemPosition();
	        	selectedFood.remove(foodId);
				handleListView();	        	
	        }
		});
	}

	public void handleListView()
	{
		foodListArray =(String[])selectedFood.toArray(new String[0]);
		final ArrayAdapter<String> ar = new ArrayAdapter<String>(ManageDiet.this, android.R.layout.simple_list_item_1, foodListArray);
		listView.setAdapter(ar);
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> a, View v, int position, long id)
			{
				AlertDialog.Builder adb=new AlertDialog.Builder(ManageDiet.this);
				adb.setTitle("Delete?");
				adb.setMessage("Are you sure you want to delete " +"\"" +listView.getItemAtPosition(position).toString() +"\"");
				final int positionToRemove = position;
				adb.setNegativeButton("Cancel", null);
				adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
				{
					public void onClick(DialogInterface dialog, int which) 
					{
						selectedFood.remove(positionToRemove);
						handleListView();
					}
				});
				adb.show();
			}
		});	
	}
	public void initSpinner()
	{
		 // call database handler
	    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	
	    // Spinner Drop down elements
	    List<String> foods = db.getFoods();
	
	    // Creating adapter for spinner
	    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	            android.R.layout.simple_spinner_dropdown_item, foods);
		
	    // Drop down layout style - list view with radio button
	    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
	    // attaching data adapter to spinner
	    spinner.setAdapter(dataAdapter);
	}
		
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) 
	{
		// On selecting a spinner item
		String food = parent.getItemAtPosition(position).toString();
			
	    // Showing selected spinner item
	    Toast.makeText(parent.getContext(), "You selected: " + food,
	            Toast.LENGTH_LONG).show();	    
	}
		
	public void onNothingSelected(AdapterView<?> arg0) {}
	
	public void initTextBox(int fieldId, String prefKey)
	{
		if(settings.contains(prefKey))
		{
			TextView view = (TextView)findViewById(fieldId);
			String text = settings.getString(prefKey, "");
			view.setText("" +text);
		}
	}
}
