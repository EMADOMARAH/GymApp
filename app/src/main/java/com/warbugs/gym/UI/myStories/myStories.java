package com.warbugs.gym.UI.myStories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.warbugs.gym.Adapters.MyStoriesAdapter;
import com.warbugs.gym.Models.MyStoriesModel;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.ResponseModels.MyStoriesResponse;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.addStory.addStory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myStories extends AppCompatActivity {
    RecyclerView myStoriesRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MyStoriesModel> myStoriesModelList;
    FloatingActionButton fab;

    ApiInterface apiInterface;
    Retrofit retrofit;
    MyStoriesModel myStoriesModel;
    SharedPreferences preferences ;
    String TOKEN;
    private int pointer = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stories);

        preferences =getSharedPreferences("GYM_APP", Context.MODE_PRIVATE);
        TOKEN = preferences.getString("TOKEN" , "");

        myStoriesRecycler = findViewById(R.id.my_stories_feedRecycler);
        layoutManager     = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        myStoriesRecycler.setLayoutManager(layoutManager);
        fab = findViewById(R.id.my_stories_fab);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://fit-way.mohameek-eg.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        getStories();

        myStoriesModelList = new ArrayList<MyStoriesModel>();

        myStoriesModelList.add(new MyStoriesModel(R.drawable.pic1, "Emad", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "2 hours ago",R.drawable.pic1));

        myStoriesModelList.add(new MyStoriesModel(R.drawable.pic1, "Emad", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "2 hours ago",R.drawable.pic1));
        myStoriesModelList.add(new MyStoriesModel(R.drawable.pic1, "Emad", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "2 hours ago",R.drawable.pic1));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , addStory.class );
                startActivity(intent);
            }
        });

        MyStoriesAdapter myStoriesAdapter = new MyStoriesAdapter(myStoriesModelList);
        myStoriesRecycler.setAdapter(myStoriesAdapter);

    }

    private void getStories() {
        apiInterface = retrofit.create(ApiInterface.class);

        Call<MyStoriesResponse> getMyStories = apiInterface.getMyStories("Bearer " + TOKEN);
        getMyStories.enqueue(new Callback<MyStoriesResponse>() {
            @Override
            public void onResponse(Call<MyStoriesResponse> call, Response<MyStoriesResponse> response) {
                if (response.isSuccessful()){
                    for (pointer=0 ; pointer<response.body().getMessage().size(); pointer++){
                        String N = response.body().getMessage().get(pointer).getName().toString();
                        String description = response.body().getMessage().get(pointer).getDescription().toString();


                    }
                }
            }

            @Override
            public void onFailure(Call<MyStoriesResponse> call, Throwable t) {

            }
        });
    }
}