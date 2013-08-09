package com.example.livewell;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Helper to the database, manages versions and creation */
public class SQLHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "foodpp.db";
	private static final int DATABASE_VERSION = 1;

	// Table name
	public static final String food_TABLE = "foodpp";
	
	// people columns
	public static final String fdName = "fd_name";
	public static final String nastygram = "nasty_gram";
	public static final String calCoo = "calCount";
	public static final String palPoo = "paleoQQ";
	
	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql_foods = "create table " + food_TABLE + "( " + 
				food_TABLE + " integer primary key autoincrement, " +
				fdName + " text not null, " + 
				nastygram + " text not null, " +
				calCoo + " text not null, " +
				palPoo + " text not null, " +
				"unique (" + fdName + ", " + nastygram + "));";
		
		db.execSQL(sql_foods);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{	
	}
	
	public long addfoodfdToDB(String foodName, String nastyGram, String clCoo, String plPoo )
	{
		ContentValues values = new ContentValues();
        values.put(SQLHelper.fdName, foodName);
        values.put(SQLHelper.nastygram, nastyGram);
        values.put(SQLHelper.calCoo, clCoo);
        values.put(SQLHelper.palPoo, plPoo);
        
        long foodfdId = getWritableDatabase().insert(food_TABLE, null, values);
        
        return foodfdId;
	}
	
	public ArrayList<Foodrs> getStuff()
	{
		ArrayList<Foodrs> foodzz = new ArrayList<Foodrs>();
		
        Cursor fdrsCursor = getReadableDatabase().query(SQLHelper.food_TABLE, 
        		new String[] {"*"}, "", null, null, null, null);
        
        int fdrsId = -1;
        Foodrs foodrs;
        
        //cycle through each row
		while(fdrsCursor.moveToNext())
		{
			fdrsId = fdrsCursor.getInt(0);
					
			if(fdrsId != -1)
			{
				foodrs = new Foodrs(fdrsId, fdrsCursor.getString(1), fdrsCursor.getString(2));
				
				foodzz.add(foodrs);	//add the patients to a list of patients
			}
		}
		
		return foodzz;
	}
	
	public void deleteStuff()
	{
		long id = getWritableDatabase().delete(food_TABLE,null, null);
	}
}