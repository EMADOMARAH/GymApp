package com.warbugs.gym.Network.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class ProfileItem{

	@SerializedName("firstname")
	private String firstname;

	@SerializedName("active_status")
	private int activeStatus;

	@SerializedName("birthdate")
	private String birthdate;

	@SerializedName("gender")
	private int gender;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("photo")
	private String photo;

	@SerializedName("email")
	private String email;

	@SerializedName("lastname")
	private String lastname;

	@SerializedName("status")
	private int status;

	public String getFirstname(){
		return firstname;
	}

	public int getActiveStatus(){
		return activeStatus;
	}

	public String getBirthdate(){
		return birthdate;
	}

	public int getGender(){
		return gender;
	}

	public String getPhone(){
		return phone;
	}

	public String getName(){
		return name;
	}

	public String getPhoto(){
		return photo;
	}

	public String getEmail(){
		return email;
	}

	public String getLastname(){
		return lastname;
	}

	public int getStatus(){
		return status;
	}
}