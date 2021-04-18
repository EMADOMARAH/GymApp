package com.warbugs.gym.Network;

import androidx.constraintlayout.widget.Barrier;

import com.warbugs.gym.Network.RequiestModels.AddStoryModel;
import com.warbugs.gym.Network.RequiestModels.LoginModel;
import com.warbugs.gym.Network.RequiestModels.RegisterModel;
import com.warbugs.gym.Network.ResponseModels.AddStoryResponse;
import com.warbugs.gym.Network.ResponseModels.BmiResponse;
import com.warbugs.gym.Network.ResponseModels.MyStoriesResponse;
import com.warbugs.gym.Network.ResponseModels.Response;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("register")
    public Call<Response> register(@Body RegisterModel registerModel);

    @POST("login")
    public Call<Response> login(@Body LoginModel loginModel);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("story/create")
    public Call<AddStoryResponse> addStory(
            @Header("Authorization") String header
           ,@Body AddStoryModel addStoryModel);

    @GET("story/all")
    public Call<MyStoriesResponse> getMyStories(
            @Header("Authorization") String header
    );



    @GET("https://fitness-calculator.p.rapidapi.com/bmi")
    public Call<BmiResponse>calcBmi(
            @Header("X-RapidAPI-Key") String header,
            @Query("weight") double weight,
            @Query("age") double age,
            @Query("height") double height);
}
