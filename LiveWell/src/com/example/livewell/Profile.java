package com.example.livewell;

import android.os.Bundle;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends BaseActivity 
{
	private EditText heightInFeet, heightInInches, age, currentWeight, goalWeight;
	private double exerciseIndex;
	private RadioGroup radioGender, radioExercise;
	private RadioButton radioGenderButton, radioExerciseButton;
	private CheckBox chkAgreement;
	private Button submitButton;
	final Context context = this;
	
	SharedPreferences settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		
		settings = getSharedPreferences(SETTINGS_PREFS, Context.MODE_APPEND);
		
		initTextBox(R.id.editText_ft, SETTINGS_PREFS_HEIGHT_FT);
		initTextBox(R.id.editText_in, SETTINGS_PREFS_HEIGHT_IN);
		initTextBox(R.id.editText_lbs, SETTINGS_PREFS_WEIGHT);
		initTextBox(R.id.editText_yrs, SETTINGS_PREFS_AGE);
		initTextBox(R.id.editText_enterGoal, SETTINGS_PREFS_GOAL_WEIGHT);
		initRadioGroup(SETTINGS_PREFS_GENDER_ID);
		initRadioGroup(SETTINGS_PREFS_EXERCISE_ID);

		addListenerOnButton();
	}
	
	public void initTextBox(int fieldId, String prefKey)
	{
		if(settings.contains(prefKey))
		{
			EditText editText = (EditText)findViewById(fieldId);
			String text = settings.getString(prefKey, "");
			editText.setText(text);
		}
	}
	
	public void initRadioGroup(String prefKey)
	{
		if(settings.contains(prefKey))
		{
			int id = settings.getInt(prefKey, 0);
			RadioButton radioButton = (RadioButton)findViewById(id);
			radioButton.setChecked(true);
		}
	}
	
	public void addListenerOnButton()
	{
		heightInFeet = (EditText)findViewById(R.id.editText_ft);
		heightInInches = (EditText)findViewById(R.id.editText_in);
		currentWeight = (EditText)findViewById(R.id.editText_lbs);
		age = (EditText)findViewById(R.id.editText_yrs);
		goalWeight = (EditText)findViewById(R.id.editText_enterGoal);
		radioGender = (RadioGroup)findViewById(R.id.radioGender);
		radioExercise = (RadioGroup)findViewById(R.id.radioExercise);
		chkAgreement = (CheckBox)findViewById(R.id.chkAgreement);
		submitButton = (Button)findViewById(R.id.btnSubmit);
		
		submitButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// get height
				String heightFt = heightInFeet.getText().toString().trim();
				String heightIn = heightInInches.getText().toString().trim();
				
				// get weight
				String initialWeight = currentWeight.getText().toString().trim();
				String wantWeight = goalWeight.getText().toString().trim();
				
				// get age
				String currentAge = age.getText().toString().trim();
				
				// get gender
				int radioGenderId = radioGender.getCheckedRadioButtonId();
				radioGenderButton = (RadioButton)findViewById(radioGenderId);
				String gender = radioGenderButton.getText().toString().trim();
				
				// get exercise preference
				int radioExerciseId = radioExercise.getCheckedRadioButtonId();
				radioExerciseButton = (RadioButton)findViewById(radioExerciseId);
				String exercisePref = radioExerciseButton.getText().toString().trim();
				
				if(chkAgreement.isChecked())
				{
					// convert String to integer
					if(heightFt.matches("") || heightIn.matches("") || initialWeight.matches("") 
							|| currentAge.matches("") || wantWeight.matches(""))
					{
						Toast.makeText(Profile.this, "Please fill in the required fields!", Toast.LENGTH_SHORT).show();
						return;
					}
					else
					{
						int heightToInt = (Integer.valueOf(heightFt)*12)+Integer.valueOf(heightIn);
						int weightToInt = Integer.valueOf(initialWeight);
						int ageToInt = Integer.valueOf(currentAge);
						int wantWeightToInt = Integer.valueOf(wantWeight);
						int weightLost = weightToInt-wantWeightToInt;
					
						// define exercise index
						if(exercisePref.equals("Sedentary"))
						{
							exerciseIndex = 1.2;
						}
						else if(exercisePref.equals("Lightly active"))
						{
							exerciseIndex = 1.375;
						}
						else if(exercisePref.equals("Moderately active"))
						{
							exerciseIndex = 1.55;
						}
						else if(exercisePref.equals("Very active"))
						{
							exerciseIndex = 1.725;
						}
						else if(exercisePref.equals("Extra active"))
						{
							exerciseIndex = 1.9;
						}
						
						// set custom dialog
						final Dialog dietDialog = new Dialog(context);
						dietDialog.setContentView(R.layout.activity_dialog);
						dietDialog.setTitle("LiveWell analysis:");
						
						ImageView image = (ImageView)dietDialog.findViewById(R.id.image_androideatapple);
						image.setImageResource(R.drawable.androideatapple);
						
						// set custom dialog components
						TextView text1 = (TextView)dietDialog.findViewById(R.id.textview_BMR);
						if(gender.equals("Male"))
						{			
							double maleBMR = 66+(6.23*wantWeightToInt)+(12.7*heightToInt)-(6.8*ageToInt);
							text1.setText("Your Basal Metabolic Rate is " +String.format("%.2f",maleBMR));
							
							TextView text2 = (TextView)dietDialog.findViewById(R.id.textview_maintainWeight);
							text2.setText("To maintain your current weight, you will need to intake "
										+String.format("%.2f",(maleBMR*exerciseIndex)) +" calories daily.");
							
							TextView text3 = (TextView)dietDialog.findViewById(R.id.textview_loseWeight);
							text3.setText("To lose " +weightLost +" lbs in 3 months, you will need to reduce your daily calories intake to "
										+String.format("%.2f",((maleBMR*exerciseIndex)-(weightLost*3500)/90))+".");
		
						}
						else if(gender.equals("Female"))
						{
							double femaleBMR = 655+(4.35*wantWeightToInt)+(4.7*heightToInt)-(4.7*ageToInt);
							text1.setText("Your Basal Metabolic Rate is " +String.format("%.2f",femaleBMR));
							
							TextView text2 = (TextView)dietDialog.findViewById(R.id.textview_maintainWeight);
							text2.setText("To maintain your current weight, you will need to intake "
										+String.format("%.2f",(femaleBMR*exerciseIndex)) +" calories daily.");
							
							TextView text3 = (TextView)dietDialog.findViewById(R.id.textview_loseWeight);
							text3.setText("To lose " +weightLost +" lbs in 3 months, you will need to reduce your daily calories intake to "
										+String.format("%.2f",((femaleBMR*exerciseIndex)-(weightLost*3500)/90))+".");
						}
						
						Button recalculateButton = (Button)dietDialog.findViewById(R.id.btn_recalculate);
						// if button is clicked, close the custom dialog
						recalculateButton.setOnClickListener(new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								dietDialog.dismiss();
							}
							
						});
	
						Button acceptButton = (Button)dietDialog.findViewById(R.id.btn_accept);
						// if button is clicked, go to the next activity
						acceptButton.setOnClickListener(new OnClickListener()
						{
							@Override
							public void onClick(View v) 
							{
								startActivity(new Intent(Profile.this, SampleDiet.class));
								finish();
							}
							
						});
						
						dietDialog.show();
						}
					}
				else
				{
					Toast.makeText(Profile.this, "Please read and agree to the Disclaimer!", Toast.LENGTH_SHORT).show();
					return;
				}
			}
		}
		);
	}
	
	public void saveTextToPrefs(int editTextId, String prefKey)
	{
		//get text from text box
		EditText editText = (EditText)findViewById(editTextId);
		String text = editText.getText().toString().trim();
		
		//save text to shared preferences
		Editor editor = settings.edit();
		editor.putString(prefKey, text);
		editor.commit();
	}

	public void saveRadioToPrefs(int radioGroupId, String prefKeyString, String prefKeyId)
	{
		RadioGroup radioGroup = (RadioGroup)findViewById(radioGroupId);
		int selectedId = radioGroup.getCheckedRadioButtonId();
		RadioButton radioButton = (RadioButton)findViewById(selectedId);
		String radioText = radioButton.getText().toString();
		
		Editor editor = settings.edit();
		editor.putString(prefKeyString, radioText);
		editor.putInt(prefKeyId, selectedId);
		editor.commit();
	}
}