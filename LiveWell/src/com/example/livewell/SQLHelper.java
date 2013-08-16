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
		ContentValues vlu = new ContentValues();
		
        vlu.put(fdName, "Chick peas-canned");
        vlu.put(nastygram, "Rome wasnt built in a day-but u got fat anyway, s'up w'dat??");
        vlu.put(calCoo,"120");
        vlu.put(palPoo,"?");
        db.insert(food_TABLE,fdName,vlu);
	
        vlu.put(fdName, "Garbonzo beans-canned");
	    vlu.put(nastygram, "today don't have any refined white sugar, ok, today?");
	    vlu.put(calCoo, "120");
	    vlu.put(palPoo, "?");
	    db.insert(food_TABLE,fdName,vlu);				
			
	    vlu.put(fdName, "milk, whole, organic");
        vlu.put(nastygram, "today take the stairs instead of the elevator, ok, today?");
	    vlu.put(calCoo,"150");
	    vlu.put(palPoo,"yes");
	    db.insert(food_TABLE,fdName,vlu);

        vlu.put(fdName, "soy milk, organic"); 
        vlu.put(nastygram, "today don't eat after 7pm, ok, today?");
        vlu.put(calCoo, "90");
	    vlu.put(palPoo, "?");
	    db.insert(food_TABLE,fdName,vlu);
			
	    vlu.put(fdName, "Diet Coke");
	    vlu.put(nastygram, "today drink 8 glasses of water, ok, today?");
	    vlu.put(calCoo, "00");
	    vlu.put(palPoo, "no");
	    db.insert(food_TABLE,fdName,vlu);
			
	    vlu.put(fdName, "wine, rose");
	    vlu.put(nastygram, "today do not have any alcohol, ok - today?");
	    vlu.put(calCoo, "71");
	    vlu.put(palPoo, "no");
	    db.insert(food_TABLE,fdName,vlu);
				
	    vlu.put(fdName, "wine, white");
	    vlu.put(nastygram, "today walk 1 mile (about 12 city blocks), ok-today?");
	    vlu.put(calCoo, "68");
	    vlu.put(palPoo, "no");
	    db.insert(food_TABLE,fdName,vlu);
			
        vlu.put(fdName, "cereal, rice krispies");
	    vlu.put(nastygram, "today do an excersize that makes you sweat, ok today?");
	    vlu.put(calCoo, "130");
	    vlu.put(palPoo, "no");
	    db.insert(food_TABLE,fdName,vlu);
				
	    vlu.put(fdName, "cheez-its");
	    vlu.put(nastygram, "today have 1/2 of what you usually eat for breakfast, ok today?");
	    vlu.put(calCoo, "140");
	    vlu.put(palPoo, "no");
	    db.insert(food_TABLE,fdName,vlu);
				
	    vlu.put(fdName, "hot dog buns, white");
	    vlu.put(nastygram, "For being totally delinquent,  what is holding you back from movn yo backside to exercise a bit?");
	    vlu.put(calCoo, "110");
	    vlu.put(palPoo, "no");
	    db.insert(food_TABLE,fdName,vlu);
				
	    vlu.put(fdName, "tortilla chips");
	    vlu.put(nastygram, "For being totally delinquent,  what is holding you back from movn yo backside to exercise a bit?");
	    vlu.put(calCoo, "140");
	    vlu.put(palPoo, "?");
	    db.insert(food_TABLE,fdName,vlu);
	
	    vlu.put(fdName, "wasa crisp bread/light rye");
	    vlu.put(nastygram, "today only eat foods which are paleo, at least today ok?");
	    vlu.put(calCoo, "70");
	    vlu.put(palPoo, "yes");
	    db.insert(food_TABLE,fdName,vlu);
	
	    vlu.put(fdName, "oatmeal");
	    vlu.put(nastygram, "today only eat vegan, only vegan today, ok?");
	    vlu.put(calCoo, "150");
	    vlu.put(palPoo, "?");
	    db.insert(food_TABLE,fdName,vlu);
				
	    vlu.put(fdName, "cheese, cheddar");
	    vlu.put(nastygram, "today lets cut the cheese, i mean cut the cheese down a bit, ok?");
	    vlu.put(calCoo, "110");
	    vlu.put(palPoo, "?");
	    db.insert(food_TABLE,fdName,vlu);

        vlu.put(fdName, "Ice cream, organic, Americone Dream");
	    vlu.put(nastygram, "today go on the internet and find fellow dieters to chat with, today ok?");
	    vlu.put(calCoo, "280");
	    vlu.put(palPoo, "?");
	    db.insert(food_TABLE,fdName,vlu);
			
	    vlu.put(fdName, "Yogurt, fruit, low fat, 11 grams protein per 8 ounce");
	    vlu.put(nastygram, "today weight yourself and vow to be at least a pound lighter in 1 week, today ok?");
	    vlu.put(calCoo, "105");
	    vlu.put(palPoo, "no");
	    db.insert(food_TABLE,fdName,vlu);

        vlu.put(fdName, "Yogurt, plain, organic");
	    vlu.put(nastygram, "today weight yourself and vow to be at least a pound lighter in 1 week, today ok?");
	    vlu.put(calCoo, "150");
	    vlu.put(palPoo, "yes");
	    db.insert(food_TABLE,fdName,vlu);

        vlu.put(fdName, "Yogurt, plain, organic");
	    vlu.put(nastygram, "today weight yourself and vow to be at least a pound lighter in 1 week, today ok?");
	    vlu.put(calCoo, "150");
	    vlu.put(palPoo, "yes");
	    db.insert(food_TABLE,fdName,vlu);

        vlu.put(fdName, "Crustaceans, crab, alaska king, raw");
        vlu.put(nastygram, "today throw away 2 things from your kitchen cubboards that are bad for you, today ok?");
	    vlu.put(calCoo, "84");
	    vlu.put(palPoo, "yes");
	    db.insert(food_TABLE,fdName,vlu);
	
        vlu.put(fdName, "Finfish, salmon, coho, wild, raw");
        vlu.put(nastygram, "today chew everything you eat at least 8 times and then once more, today ok?");
	    vlu.put(calCoo, "146");
	    vlu.put(palPoo, "yes");
	    db.insert(food_TABLE,fdName,vlu);
       			    
        vlu.put(fdName, "Mollusks, scallop, mixed species, raw");
        vlu.put(nastygram, "Every voyage starts with just a small step and you total flunked step class!!");
	    vlu.put(calCoo, "88");
	    vlu.put(palPoo, "yes");
	    db.insert(food_TABLE,fdName,vlu);
																			
        vlu.put(fdName, "tuna, 1 can chunk light in water");
        vlu.put(nastygram, "today don't have foods containing msg-(monosodium glutamate), today ok?");
	    vlu.put(calCoo, "50");
	    vlu.put(palPoo, "?");
	    db.insert(food_TABLE,fdName,vlu);


	    vlu.put(fdName, "Cranberries, raw");
    	vlu.put(nastygram, "today have tea instead of coffee, today ok?");
	    vlu.put(calCoo, "49");
	    vlu.put(palPoo, "yes");
	    db.insert(food_TABLE,fdName,vlu);

		vlu.put(fdName, "Fruit cocktail, (peach and pineapple and pear and grape and cherry), canned, extra heavy syrup, solids and liquids");
		vlu.put(nastygram, "today do not drink more than 3 cups of coffee, only three today ok?");
	    vlu.put(calCoo, "88");
	    vlu.put(palPoo, "no");
        db.insert(food_TABLE,fdName,vlu);
		    
        vlu.put(fdName, "Grapefruit, raw, white, California");
        vlu.put(nastygram, "today buy fresh fruit and make a fruit bowl, today ok?");
	    vlu.put(calCoo, "37");
	    vlu.put(palPoo, "yes");
        db.insert(food_TABLE,fdName,vlu);
																											

        vlu.put(fdName, "Kiwi fruit, (chinese gooseberries), fresh, raw");
        vlu.put(nastygram, "today drink your coffee or tea without adding sweetener or anything, today ok?");
	    vlu.put(calCoo, "61");
	    vlu.put(palPoo, "yes");
        db.insert(food_TABLE,fdName,vlu);

		vlu.put(fdName, "Oranges, raw, California, navels");
		vlu.put(nastygram, "today have a cup of herbal tea, yeah today ok?");
		vlu.put(calCoo, "37");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Quinces, raw");
		vlu.put(nastygram, "today ask a friend or a relative for a healthy recipe, yes today ok?");
		vlu.put(calCoo, "57");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "raisens, seedless");
		vlu.put(nastygram, "It's not what you eat that makes you skinny, it's what you DONT eat, so stop already!!");
		vlu.put(calCoo, "90");
		vlu.put(palPoo, "no");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Tangerines, (mandarin oranges), raw");
		vlu.put(nastygram, "today try to associate happiness with a type of exercise, today ok?");
		vlu.put(calCoo, "44");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Watermelon, raw");
		vlu.put(nastygram, "today do not have anything that is deep fried, not today ok?");
		vlu.put(calCoo, "32");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Chili con carne");
		vlu.put(nastygram, "today do not eat anything that has gmo, genetically modified organism, today ok?");
		vlu.put(calCoo, "280");
		vlu.put(palPoo, "no");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "pizza, margherita,organic");
		vlu.put(nastygram, "today do not drink coffee with your meal, today ok?");
		vlu.put(calCoo, "280");
		vlu.put(palPoo, "?");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "soup, canned chicken soup");
		vlu.put(nastygram, "today do not have any snacks, only 3 meals, today ok?");
		vlu.put(calCoo, "80");
		vlu.put(palPoo, "no");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "hot dogs, kosher");
		vlu.put(nastygram, "today drink bottled water without flouride or chlorine, today ok?");
		vlu.put(calCoo, "150");
		vlu.put(palPoo, "no");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "mayonnaise");
		vlu.put(nastygram, "I guess some people just download weight loss apps for no apparent reason…wouldnt be you would it?");
		vlu.put(calCoo, "100");
		vlu.put(palPoo, "?");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "olive oil, virgin cold pressed");
		vlu.put(nastygram, "today eat your eggs before your toast for breakfast, today ok?");
		vlu.put(calCoo, "120");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "tarter sauce, store bought");
		vlu.put(nastygram, "today reward yourself for dieting - but not with food, today ok?");
		vlu.put(calCoo, "170");
		vlu.put(palPoo, "no");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "honey, organic");
		vlu.put(nastygram, "today go to a movie and bring your own low-cal snacks, today ok?");
		vlu.put(calCoo, "60");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "salsa, qfc brand");
		vlu.put(nastygram, "today do not have anything that has preservatives, today ok?");
		vlu.put(calCoo, "10");
		vlu.put(palPoo, "?");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Tomato sauce, organic");
		vlu.put(nastygram, "today eat only food that is locally grown and prepared, today ok?");
		vlu.put(calCoo, "60");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "ravioli, cheese - no sauce");
		vlu.put(nastygram, "today put an ad in the paper for a jogging partner, today ok?");
		vlu.put(calCoo, "340");
		vlu.put(palPoo, "?");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "sugar, white");
		vlu.put(nastygram, "today talk to someone about what healthy eating means, ok today?");
		vlu.put(calCoo, "15");
		vlu.put(palPoo, "?");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Artichokes, boiled, drained, with salt");
		vlu.put(nastygram, "today do not eat fat and sugar at the same time, today ok?");
		vlu.put(calCoo, "50");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "beets, boiled");
		vlu.put(nastygram, "show your app some luv for a change - when are you going to get serious about what Im posting here??");
		vlu.put(calCoo, "44");
		vlu.put(palPoo, "no");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Brussels sprouts, cooked, boiled, drained, with salt");
		vlu.put(nastygram, "today do not eat anything that has rbgh, (recumbant bovine growth hormone), today ok?");
		vlu.put(calCoo, "41");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Cabbage, boiled");
		vlu.put(nastygram, "today eat only foods that have been prepared by hand, today ok?");
		vlu.put(calCoo, "20");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Cabbage, boiled,with salt");
		vlu.put(nastygram, "today buy a piece of clothing for when you are skinny, today ok?");
		vlu.put(calCoo, "22");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Carrots, raw");
		vlu.put(nastygram, "What do you think this is, a game? How about the game of life and your loosing!!");
		vlu.put(calCoo, "43");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Cauliflower, boiled");
		vlu.put(nastygram, "today dance to your favorite song, shall we dance you and your app, ok?");
		vlu.put(calCoo, "23");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Celery, raw");
		vlu.put(nastygram, "today have a low calorie meal from another country, enjoy; its on your app, ok?");
		vlu.put(calCoo, "16");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Mushrooms, raw");
		vlu.put(nastygram, "today have a low calorie meal from another country, enjoy; its on your app, ok?");
		vlu.put(calCoo, "25");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
			
		vlu.put(fdName, "Mushrooms, shiitake, cooked, with salt");
		vlu.put(nastygram, "today do not have any food that contains high fructose corn syrup, today ok?");
		vlu.put(calCoo, "55");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Okra, cooked, boiled, drained, with salt");
		vlu.put(nastygram, "Your just rationalized your diet away, like you did yesterday and the day before that!!");
		vlu.put(calCoo, "32");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Onions, raw");
		vlu.put(nastygram, "today do not have any food that contains high fructose corn syrup, today ok?");
		vlu.put(calCoo, "38");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
		vlu.put(fdName, "Vegetables, mixed, frozen, cooked, boiled, drained, with salt");
		vlu.put(nastygram, "today do not have any bread, pasta or potatos, today ok?");
		vlu.put(calCoo, "59");
		vlu.put(palPoo, "no");
		db.insert(food_TABLE,fdName,vlu);
			
		vlu.put(fdName, "potato, russet, baked");
		vlu.put(nastygram, "You know those total fat sopranos in sunday skoo choir(?) thats where youre headed accept you cant sing!!");
		vlu.put(calCoo, "110");
		vlu.put(palPoo, "yes");
		db.insert(food_TABLE,fdName,vlu);
		
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