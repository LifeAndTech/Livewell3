package com.example.livewell;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends BaseActivity 
{
	private TextView nastyGram;
	
	SharedPreferences settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_APPEND);
		
		initTextBox(R.id.textView_lwlogo, SETTINGS_PREFS_LW_CALORIES);
		
		addListenerOnButton(R.id.btn_calories, ManageDiet.class);
		addListenerOnButton(R.id.btn_calendar, SampleCalendar.class);
		addListenerOnButton(R.id.btn_calculator, Calculator.class);
		addListenerOnButton(R.id.btn_foodsnap, FoodSnapshot.class);
		addListenerOnButton(R.id.btn_profile, Profile.class);
		addListenerOnButton(R.id.btn_database, SampleDiet.class);	
	}
	
	public void initTextBox(int fieldId, String prefKey)
	{
		if(settings.contains(prefKey))
		{
			TextView view = (TextView)findViewById(fieldId);
			String text = settings.getString(prefKey, "");
			view.setText("Your daily calories intake is: " +text);
		}
	}

	public void addListenerOnButton(int btnID, final Class destination)
	{
		Button button = (Button)findViewById(btnID);
		
		button.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{				
				startActivity(new Intent(MainActivity.this, destination));
			}
		});
	}
	

}
