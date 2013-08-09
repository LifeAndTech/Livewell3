package com.example.livewell;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddRecord extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_record);
		
        //init save button
        Button saveButton = (Button) findViewById(R.id.Button_SaveRecord);
        saveButton.setOnClickListener(new SaveClickListener());
	}

	/*
	 * if all the patient parameters were entered correctly it attempts to add the patient to the db
	 */
	private class SaveClickListener implements View.OnClickListener
    {
    	public void onClick(View v) 
    	{	
    		String foodName = showHideNameError(R.id.EditText_foodName, R.id.TextView_efoodName);
    		String nastyName = showHideNameError(R.id.EditText_nastyGram, R.id.TextView_enastyGram);
    		String calCoo = showHideNameError(R.id.EditText_calCount, R.id.TextView_efoodName);
    		String palPoo= showHideNameError(R.id.EditText_palPoo, R.id.TextView_enastyGram);
    		
        	if( foodName == null || nastyName == null)
        	{
        		Toast errorEntry = Toast.makeText(AddRecord.this, 
        				"Settings were entered incorectly", Toast.LENGTH_LONG);
        		errorEntry.show();
        	}
        	
        	else
        	{
        		long fooddbId =  new SQLHelper(AddRecord.this).addfoodfdToDB(foodName, nastyName, calCoo, palPoo);
        		
        		if(fooddbId == -1)
        		{
        			Toast successEntry = Toast.makeText(AddRecord.this, "Error adding yo food!!", Toast.LENGTH_LONG);
        			return;
        		}
        		
                 Toast successEntry = Toast.makeText(AddRecord.this, "Yo food z n ther mo!!", Toast.LENGTH_LONG);
	        	 successEntry.show();
               
        	}
        }
    }
	 
	
	private String showHideNameError(int editTextId, int errorTextViewId)
    {
    	EditText nameBox = (EditText)findViewById(editTextId);
    	String name = nameBox.getText().toString().trim();
    	
    	TextView nameError = (TextView)findViewById(errorTextViewId);
    	
    	if(name.length() <= 0)
    	{
    		/* show error if the name isn't at least 3 characters */
    		nameError.setText(" Error ");
    		return null;
    	}
    	else
    	{
    		/* clear error if the name isn't at least 3 characters */
    		nameError.setText("");
    		return name;
    	}	
    }
}