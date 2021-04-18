package com.warbugs.gym.Network.ResponseModels;

import com.google.gson.annotations.SerializedName;

public class BmiResponse{

	@SerializedName("healthy_bmi_range")
	private String healthyBmiRange;

	@SerializedName("health")
	private String health;

	@SerializedName("bmi")
	private double bmi;

	public String getHealthyBmiRange(){
		return healthyBmiRange;
	}

	public String getHealth(){
		return health;
	}

	public double getBmi(){
		return bmi;
	}
}