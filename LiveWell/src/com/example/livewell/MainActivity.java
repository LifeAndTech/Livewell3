package com.example.livewell;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity 
{
	private Button toCalories, toCalendar, calculator, toPaleo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
	}
	
	public void addListenerOnButton()
	{
		toCalories = (Button)findViewById(R.id.btn_calories);
		toCalendar = (Button)findViewById(R.id.btn_calendar);
		calculator = (Button)findViewById(R.id.btn_calculator);
		toPaleo = (Button)findViewById(R.id.btn_foodsnap);
		
		toCalories.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{				
				if(toCalories.isPressed())
				{
					startActivity(new Intent(MainActivity.this, Profile.class));
				}
				else
				{
					Toast.makeText(MainActivity.this, "click somewhere else", Toast.LENGTH_SHORT).show();
				}
			}
		}
		);
		
		toCalendar.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(toCalendar.isPressed())
				{
					startActivity(new Intent(MainActivity.this, SampleCalendar.class));
				}
				else
				{
					Toast.makeText(MainActivity.this, "click somewhere else", Toast.LENGTH_SHORT).show();
				}
			}
		}
		);
		
		calculator.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(calculator.isPressed())
				{
					Toast.makeText(MainActivity.this, "Diet is the only game where you win when you lose!",
							Toast.LENGTH_SHORT).show();
					//startActivity(new Intent(MainActivity.this, Profile.class));
				}
				else
				{
					Toast.makeText(MainActivity.this, "click somewhere else", Toast.LENGTH_SHORT).show();
				}
			}
		}
		);
		
		toPaleo.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(toPaleo.isPressed())
				{
					startActivity(new Intent(MainActivity.this, FoodSnapshot.class));
				}
				else
				{
					Toast.makeText(MainActivity.this, "click somewhere else", Toast.LENGTH_SHORT).show();
				}
			}
		}
		);
	}
	

}
