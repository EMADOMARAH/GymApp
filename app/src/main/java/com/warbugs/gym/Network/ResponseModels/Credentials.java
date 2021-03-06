package com.warbugs.gym.Network.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class Credentials{

	@SerializedName("access_token")
	private String accessToken;

	@SerializedName("token_type")
	private String tokenType;

	@SerializedName("expires_in")
	private long expiresIn;

	public String getAccessToken(){
		return accessToken;
	}

	public String getTokenType(){
		return tokenType;
	}

	public long getExpiresIn(){
		return expiresIn;
	}
}