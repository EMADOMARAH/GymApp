package com.warbugs.gym.Network.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class AddStoryResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private boolean status;

	public String getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}