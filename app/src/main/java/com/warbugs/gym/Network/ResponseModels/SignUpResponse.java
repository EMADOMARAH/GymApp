package com.warbugs.gym.Network.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {

	@SerializedName("message")
	private Message message;

	@SerializedName("status")
	private boolean status;

	public Message getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}