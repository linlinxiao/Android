package com.baoxiao.model;

public class Contact
{
	int id;
	String name;
	String phone;
	boolean isChoosed;
	
	public Contact(){}

	public Contact(int id, String name, String phone)
	{
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	public boolean isChoosed()
	{
		return isChoosed;
	}

	public void setChoosed(boolean isChoosed)
	{
		this.isChoosed = isChoosed;
	}
	
}
