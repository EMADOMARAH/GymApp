package com.warbugs.gym.Network.ResponseModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Message{

	@SerializedName("credentials")
	private Credentials credentials;

	@SerializedName("profile")
	private List<ProfileItem> profile;

	public Credentials getCredentials(){
		return credentials;
	}

	public List<ProfileItem> getProfile(){
		return profile;
	}
}