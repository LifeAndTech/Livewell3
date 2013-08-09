package com.example.livewell;

import java.util.ArrayList;

/*
 * This stores the information for each patient stored in the database
 */
public class Foodrs
{
	private int id;
	private String fdName;
	
	private String nastyNote;
	
	public Foodrs(int id, String fname, String nastnte)
	{
		this.id = id;
		this.fdName = fname;
		this.nastyNote = nastnte;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getfdName()
	{
		return fdName;
	}
	
	public String getnastyName()
	{
		return nastyNote;
	}
	
	public String getName()
	{
		return fdName + " " + nastyNote;
	}
}