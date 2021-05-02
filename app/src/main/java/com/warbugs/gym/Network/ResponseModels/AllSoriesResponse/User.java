package com.warbugs.gym.Network.ResponseModels.AllSoriesResponse;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("type_id")
	private int typeId;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("deleted_at")
	private Object deletedAt;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private int status;

	public String getUpdatedAt(){
		return updatedAt;
	}

	public int getTypeId(){
		return typeId;
	}

	public String getName(){
		return name;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public Object getDeletedAt(){
		return deletedAt;
	}

	public String getEmail(){
		return email;
	}

	public int getStatus(){
		return status;
	}
}