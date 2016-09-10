package com.baoxiao.model;

public class Collection
{
	private int id;
	private String title;
	
	public Collection(){}
	public Collection(int id, String title)
	{
		super();
		this.id = id;
		this.title = title;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	
}
