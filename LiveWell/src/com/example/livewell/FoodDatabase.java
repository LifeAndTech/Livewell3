package com.example.livewell;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodDatabase extends BaseActivity 
{
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_database);
		
        initAddRecord();
        initDeleteRecords();
	}

	public void onResume() 
	{
		super.onResume();
		initFdstuff();
	}
	
	/*
	 * get the list of patients from the database
	 * cycle through them and add them to the list view
	 */
	private void initFdstuff()
	{		
		ArrayList<Foodrs> ppFood = new SQLHelper(this).getStuff();
		
		if (ppFood.size() > 0) 
		{
			//add the patients to the list view
			ListView flView = (ListView)findViewById(R.id.ListView_ppFood);
			FdAdapt fAdapt = new FdAdapt(this, R.layout.activity_food_rows, ppFood);
			flView.setAdapter(fAdapt);			
		}
	}
	
	// method to clear list view
	private void clearListView()
	{
		ListView fdrsView = (ListView)findViewById(R.id.ListView_ppFood);
		fdrsView.setAdapter(null);
	}
     
    private void initAddRecord()
    {
    	Button addRecordTV = (Button)findViewById(R.id.Button_AddRecord);   	
    	addRecordTV.setOnClickListener(new OnClickListener(){
    		public void onClick(View v)
    		{
    		//we could pass data here quang with intent if preference not adequate!!
    			startActivity(new Intent(FoodDatabase.this, AddRecord.class));
    		}
    	});
    }
    
    private void initDeleteRecords()
    {
    	Button addRecordTV = (Button)findViewById(R.id.Button_DeleteRecords);   	
    	addRecordTV.setOnClickListener(new OnClickListener(){
    		public void onClick(View v)
    		{
    			SQLHelper sqlHelper = new SQLHelper(FoodDatabase.this);
    			sqlHelper.deleteStuff();
    			clearListView();
    		}
    	});
    }
    
    /*
     * Adds each Patient from the array of patients into the adapter. 
     * Applying the patient_row layout file to each of them
     */
    private class FdAdapt extends ArrayAdapter<Foodrs> 
    {
    	public FdAdapt(Context context, int textViewResourceId, ArrayList<Foodrs> ppFood) 
    	{
			super(context, textViewResourceId, ppFood);
		}
    	
    	@Override
		public View getView(int position, View convertView, ViewGroup parent) 
    	{
    		if (convertView == null) 
    		{
				LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = mInflater.inflate(R.layout.activity_food_rows, null);
			}
    		
    		final Foodrs foodrs = getItem(position);
    		
    		if(foodrs != null)
    		{
    			TextView nameTV = (TextView) convertView.findViewById(R.id.TextView_Name);
    			nameTV.setText(foodrs.getName());
    		}
    		
    		return convertView;
    	}
    }
}