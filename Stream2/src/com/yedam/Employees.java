package com.yedam;

public class Employees {
	private String FirstName;
	private String LastName;
	private String BirthDate;
	private String Title;
	
	public String getFirstName() {
		return FirstName;
	}
	
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	
	public String getLastName() {
		return LastName;
	}
	
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	public String getBirthDate() {
		return BirthDate;
	}
	
	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public void setTitle(String title) {
		Title = title;
	}

	@Override
	public String toString() {
		return "IT회사>>  이름:" + FirstName + ", " +LastName + "  직무:" + Title;
	}
	
	
}
