package com.warbugs.gym.Network.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class ProfileItem{

	@SerializedName("firstname")
	public String firstname;

	@SerializedName("active_status")
	public int activeStatus;

	@SerializedName("birthdate")
	public String birthdate;

	@SerializedName("gender")
	public int gender;

	@SerializedName("phone")
	public String phone;

	@SerializedName("name")
	public String name;

	@SerializedName("photo")
	public String photo;

	@SerializedName("email")
	public String email;

	@SerializedName("lastname")
	public String lastname;

	@SerializedName("status")
	public int status;

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