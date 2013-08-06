package com.example.livewell;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SampleDiet extends BaseActivity 
{
	private SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample_diet);
		
		//calling on our preferences
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		
		Button contButton = (Button)findViewById(R.id.button_continue);
		contButton.setOnClickListener(new ContButtonListener());
		
		initSpinner(R.id.spinner_desserts, R.array.desserts_array, SETTINGS_PREFS_DESERTS_ID);
	}

	public void initSpinner(int spinnerId, int arrayId, String prefKey)
	{
		Spinner spinner = (Spinner)findViewById(spinnerId);
		
		ArrayAdapter<CharSequence> adapter = 
			ArrayAdapter.createFromResource(this, arrayId, android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		if(settings.contains(prefKey))
		{
			int selectedId = settings.getInt(prefKey, 0);
			spinner.setSelection(selectedId);
		}
	}
	
	private class ContButtonListener implements View.OnClickListener 
	{
		public void onClick(View v) 
		{
			saveSpinnerToPrefs(R.id.spinner_desserts, SETTINGS_PREFS_DESERTS, SETTINGS_PREFS_DESERTS_ID);
				startActivity(new Intent(SampleDiet.this, SampleCalendar.class));
		}
	}
	
	public void saveSpinnerToPrefs(int spinnerId, String prefKeyString, String prefKeyId)
	{
		Spinner spinner = (Spinner)findViewById(spinnerId);
		int selectedId = spinner.getSelectedItemPosition();
		String text = spinner.getSelectedItem().toString();
		
		Editor editor = settings.edit();
		editor.putString(prefKeyString, text);
		editor.putInt(prefKeyId, selectedId);
		editor.commit();
	}
}
