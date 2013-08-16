package com.example.livewell;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SampleDiet extends BaseActivity implements OnItemSelectedListener
{
	private SharedPreferences settings;
	Spinner spinner;
	Button btnAdd, btnDelete;
	EditText inputFoodName, inputNastyGram, inputCalories, inputPaleo;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_diet);
		
		//calling on our preferences
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		spinner = (Spinner)findViewById(R.id.spinner);
		
		inputFoodName = (EditText)findViewById(R.id.input_foodName);
		inputNastyGram = (EditText)findViewById(R.id.input_nastyGram);
		inputCalories = (EditText)findViewById(R.id.input_clCoo);
		inputPaleo = (EditText)findViewById(R.id.input_plPoo);
		
		btnAdd = (Button)findViewById(R.id.btn_add);
		btnDelete = (Button)findViewById(R.id.btn_delete);	
		
		spinner.setOnItemSelectedListener(this);
		initSpinner();
		
		btnAdd.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
            {
            	String foodName = inputFoodName.getText().toString();
            	String nastyGram = inputNastyGram.getText().toString();
            	String calories = inputCalories.getText().toString();
            	String paleo = inputPaleo.getText().toString();
 
                // If label is not empty
            	if (foodName.trim().length() > 0) 
            	{
                    // call database handler
                    DatabaseHandler db = new DatabaseHandler(
                            getApplicationContext());
 
                    // inserting new label into database
                    db.addfoodfdToDB(foodName, nastyGram, calories, paleo);
 
                    // making input filed text to blank
                    inputFoodName.setText("");
                    inputNastyGram.setText("");
                    inputCalories.setText("");
                    inputPaleo.setText("");
 
                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputPaleo.getWindowToken(), 0);
 
                    // loading spinner with newly added data
                    initSpinner();
                } 
            	else 
            	{
                    Toast.makeText(getApplicationContext(), "Please enter label name",
                            Toast.LENGTH_SHORT).show();
                }
 
            }
		});
		
		btnDelete.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
            {
            	String foodName = (String)spinner.getSelectedItem();
 
                // If label is not empty
            	if (foodName.trim().length() > 0) 
            	{
                    // call database handler
                    DatabaseHandler db = new DatabaseHandler(
                            getApplicationContext());
 
                    // inserting new label into database
                    db.removeFoodFromDB(foodName, null, null, null);
 
 
                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(inputPaleo.getWindowToken(), 0);
 
                    // loading spinner with newly added data
                    initSpinner();
                } 
            	else 
            	{
                    Toast.makeText(getApplicationContext(), "Please enter label name",
                            Toast.LENGTH_SHORT).show();
                }
 
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

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
