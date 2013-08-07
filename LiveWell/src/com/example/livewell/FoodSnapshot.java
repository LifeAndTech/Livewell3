package com.example.livewell;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodSnapshot extends BaseActivity 
{
	private static final int TAKE_IMAGE_CAMERA_REQUEST = 1;
	private int btnClicked;
	private SharedPreferences settings;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_food_snapshot);

		//calling on our preferences
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE);
		initTextField(R.id.textview_breakfast, SETTINGS_PREFS_BREAKFAST_ID);
		initTextField(R.id.textview_breakfastDate, SETTINGS_PREFS_BREAKFAST_DATE);
		initImage(R.id.imagebutton_breakfast, SETTINGS_PREFS_BREAKFAST_IMAGE);
		initTextField(R.id.textview_lunch, SETTINGS_PREFS_LUNCH_ID);
		initTextField(R.id.textview_lunchDate, SETTINGS_PREFS_LUNCH_DATE);
		initImage(R.id.imagebutton_lunch, SETTINGS_PREFS_LUNCH_IMAGE);
		initTextField(R.id.textview_dinner, SETTINGS_PREFS_DINNER_ID);
		initTextField(R.id.textview_dinnerDate, SETTINGS_PREFS_DINNER_DATE);
		initImage(R.id.imagebutton_dinner, SETTINGS_PREFS_DINNER_IMAGE);
	}
	
	private void initImage(int imageID, String imageLocation)
	{
		ImageButton imageButton = (ImageButton)findViewById(imageID);
		
		//show the picture saved in the shared preferences
		if(settings.contains(imageLocation))
		{
			String uriString = settings.getString(imageLocation, "");
			
			if(!uriString.equals(""))
			{
				Uri imageUri = Uri.parse(uriString);
				imageButton.setImageURI(imageUri);
			}
		}
		
		imageButton.setOnClickListener(new OnClickListener() 
		{
			public void onClick(View v) 
			{
				btnClicked = v.getId();
				Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(Intent.createChooser(cameraIntent, "Take your photo"),
					TAKE_IMAGE_CAMERA_REQUEST);
			}
		});
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if(requestCode == TAKE_IMAGE_CAMERA_REQUEST)
		{
			//if they cancelled taking their picture don't attempt to save it
			if(resultCode == Activity.RESULT_CANCELED) return;
			
			if(resultCode == Activity.RESULT_OK)
			{
				//get data from camera and turn it into a bitmap
				Bitmap cameraPic = (Bitmap)intent.getExtras().get("data");
				if(cameraPic == null) return;
				
				if(btnClicked == R.id.imagebutton_breakfast)
				{
					saveFood(cameraPic, R.id.imagebutton_breakfast, SETTINGS_PREFS_BREAKFAST_IMAGE);
				}
				else if(btnClicked == R.id.imagebutton_lunch)
				{
					saveFood(cameraPic, R.id.imagebutton_lunch, SETTINGS_PREFS_LUNCH_IMAGE);
				}
				else if(btnClicked == R.id.imagebutton_dinner)
				{
					saveFood(cameraPic, R.id.imagebutton_dinner, SETTINGS_PREFS_DINNER_IMAGE);
				}
				
				SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss");
				String currentDateTime = dateTime.format(new Date());
				
				saveTextToPrefs(R.id.textview_breakfastDate, currentDateTime);
			}
		}
	}
	
	private void saveFood(Bitmap pic, int imageID, String imageLocation)
	{
		String foodImageFile = "food" +btnClicked +".png";
		
		try
		{
			pic.compress(CompressFormat.PNG, 50, openFileOutput(foodImageFile, MODE_PRIVATE));
		}
		catch(Exception e)
		{
			return;
		}
		
		//makes a new file and gives you the path
		Uri foodImageUri = Uri.fromFile(new File(this.getFilesDir(), foodImageFile));
		
		//get the image button
		ImageButton foodImageButton = (ImageButton)findViewById(imageID);
		
		//save food to button
		foodImageButton.setImageURI(null);
		foodImageButton.setImageURI(foodImageUri);
		
		//save the path to the shared preferences
		Editor editor = settings.edit();
		editor.putString(imageLocation, foodImageUri.getPath());
		editor.commit();
	}

	public void initTextField(int fieldId, String prefKey)
	{
		if(settings.contains(prefKey))
		{
			String text = settings.getString(prefKey, "");
			TextView tv = (TextView)findViewById(fieldId);
			tv.setText(text);
		}
	}
	
	public void saveTextToPrefs(int textViewId, String prefKey)
	{
		//get text from text box
		TextView editText = (TextView)findViewById(textViewId);
		String text = editText.getText().toString().trim();
		
		//save text to shared preferences
		Editor editor = settings.edit();
		editor.putString(prefKey, text);
		editor.commit();
	}
}