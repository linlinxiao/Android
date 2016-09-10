package model;

import java.util.ArrayList;

public class Result {
	private String resultCode;
	private ArrayList<Person> personsData;
	private int count;

	public int getCount() {
		if (personsData == null) return 0;
		return personsData.size();
	}

	public ArrayList<Person> getPersonsData() {
		return personsData;
	}

	public void setPersonsData(ArrayList<Person> personsData) {
		this.personsData = personsData;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
