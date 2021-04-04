package com.warbugs.gym.Network;

import com.warbugs.gym.Network.RequiestModels.LoginModel;
import com.warbugs.gym.Network.ResponseModels.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

//    @POST("register")
//    public Call<RegisterResponse> register(@Body RegisterModel registerModel);

    @POST("login")
    public Call<Response> login(@Body LoginModel loginModel);
}
