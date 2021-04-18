package com.warbugs.gym.Network.ResponseModels;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MyStoriesResponse{

	@SerializedName("message")
	private List<MessageItem> message;

	@SerializedName("status")
	private boolean status;

	public List<MessageItem> getMessage(){
		return message;
	}

	public boolean isStatus(){
		return status;
	}
}