package com.warbugs.gym.Network.ResponseModels.AllSoriesResponse;

import com.google.gson.annotations.SerializedName;

public class AllStoriesResponse{

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