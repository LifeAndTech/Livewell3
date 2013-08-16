package com.example.livewell;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Helper to the database, manages versions and creation */
public class DatabaseHandler extends SQLiteOpenHelper 
{
	// Database name
	private static final String DATABASE_NAME = "foodpp.db";
	
	// Database version
	private static final int DATABASE_VERSION = 1;

	// Table name
	public static final String food_TABLE = "foodpp";
	
	// Table columns
	public static final String fdName = "fd_name";
	public static final String nastygram = "nasty_gram";
	public static final String calCoo = "calCount";
	public static final String palPoo = "paleoQQ";
	
	public DatabaseHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		addfoodfdToDB("Chick peas-canned","Rome wasn't built in a day-but u got fat anyway, s'up w'dat??","120","?");
		addfoodfdToDB("Garbonzo beans-canned","today don't have any refined white sugar, ok, today?","120","?");
		addfoodfdToDB("milk, whole, organic","today take the stairs instead of the elevator, ok, today?","150","Yes");		
		addfoodfdToDB("soy milk, organic","today don't eat after 7pm, ok, today?","90","?");
		addfoodfdToDB("Diet Coke","today drink 8 glasses of water, ok, today?","0","No");
		addfoodfdToDB("wine, rose","today do not have any alcohol, ok - today?","71","No");
		addfoodfdToDB("wine, white","today walk 1 mile (about 12 city blocks), ok-today?","68","No");
		addfoodfdToDB("cereal, rice krispies","today do an excersize that makes you sweat, ok today?","130","No");
		addfoodfdToDB("hot dog buns, white","For being totally delinquent,  what is holding you back from movn yo backside to exercise a bit?","110","No");		
		addfoodfdToDB("tortilla chips","For being totally delinquent,  what is holding you back from movn yo backside to exercise a bit?","140","?");
		addfoodfdToDB("wasa crisp bread/light rye","today only eat foods which are paleo, at least today ok?","70","Yes");		
		addfoodfdToDB("oatmeal","today only eat vegan, only vegan today, ok?","150","?");
		addfoodfdToDB("cheese, cheddar","today lets cut the cheese, i mean cut the cheese down a bit, ok?","110","?");
		addfoodfdToDB("Ice cream, organic, Americone Dream","today go on the internet and find fellow dieters to chat with, today ok?","280","?");
		addfoodfdToDB("Yogurt, fruit, low fat, 11 grams protein per 8 ounce","today weight yourself and vow to be at least a pound lighter in 1 week, today ok?","105","No");
		addfoodfdToDB("Yogurt, plain, organic","today weight yourself and vow to be at least a pound lighter in 1 week, today ok?","150","Yes");	
	}

	public void onCreate(SQLiteDatabase db) 
	{		
		String sql_foods = "create table " + food_TABLE + "( " + 
				food_TABLE + " integer primary key autoincrement, " +
				fdName + " text not null, " + 
				nastygram + " text not null, " +
				calCoo + " text not null, " +
				palPoo + " text not null, " +
				"unique (" + fdName + ", " + nastygram + "));";
		
		db.execSQL(sql_foods); 	
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
	public void addfoodfdToDB(String foodName, String nastyGram, String clCoo, String plPoo)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
        values.put(fdName, foodName);
        values.put(nastygram, nastyGram);
        values.put(calCoo, clCoo);
        values.put(palPoo, plPoo);
        
        db.insert(food_TABLE, null, values);
		db.close();
	}
	
	public void removeFoodFromDB(String foodName, String nastyGram, String clCoo, String plPoo)
	{
		SQLiteDatabase db = this.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		
		values.put(fdName, foodName);
        values.put(nastygram, nastyGram);
        values.put(calCoo, clCoo);
        values.put(palPoo, plPoo);
		
		db.delete(food_TABLE, fdName + "=?", new String[]{foodName});
		db.close();
	}
	
	public ArrayList<String> getFoods()
	{
		ArrayList<String> foodzz = new ArrayList<String>();
		
		String selectQuery = "SELECT * FROM " + food_TABLE;
		
		SQLiteDatabase db = this.getReadableDatabase();       
		Cursor fdrsCursor = db.rawQuery(selectQuery, null);
        
        //cycle through each row
		if(fdrsCursor.moveToFirst())
		{
			do
			{
				foodzz.add(fdrsCursor.getString(1) +" - " +fdrsCursor.getString(3));
			} while(fdrsCursor.moveToNext());
		}
		
		fdrsCursor.close();
		db.close();
		
		return foodzz;
	}
}