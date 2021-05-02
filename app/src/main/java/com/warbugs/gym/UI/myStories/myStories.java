package com.warbugs.gym.UI.myStories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.warbugs.gym.Adapters.MyStoriesAdapter;
import com.warbugs.gym.Models.MyStoriesModel;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.ResponseModels.MyStoriesResponse.MyStoriesResponse;
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
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stories);

        preferences =getSharedPreferences("GYM_APP", Context.MODE_PRIVATE);
        TOKEN = preferences.getString("TOKEN" , "");

        progressBar =findViewById(R.id.progress_loader_my_stories);

        myStoriesRecycler = findViewById(R.id.my_stories_feedRecycler);
        layoutManager     = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        myStoriesRecycler.setLayoutManager(layoutManager);
        fab = findViewById(R.id.my_stories_fab);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://fit-way.mohameek-eg.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();




        myStoriesModelList = new ArrayList<MyStoriesModel>();
        getStories();

//        myStoriesModelList.add(new MyStoriesModel("R.drawable.pic1", "Emad", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "2 hours ago",R.drawable.pic1));
//
//        myStoriesModelList.add(new MyStoriesModel("R.drawable.pic1", "Emad", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "2 hours ago",R.drawable.pic1));
//        myStoriesModelList.add(new MyStoriesModel("R.drawable.pic1", "Emad", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.", "2 hours ago",R.drawable.pic1));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , addStory.class );
                startActivity(intent);

            }
        });



    }

    private void getStories() {
        apiInterface = retrofit.create(ApiInterface.class);

        Call<MyStoriesResponse> getMyStories = apiInterface.getMyStories("Bearer " + TOKEN);
        getMyStories.enqueue(new Callback<MyStoriesResponse>() {
            @Override
            public void onResponse(Call<MyStoriesResponse> call, Response<MyStoriesResponse> response) {
                if (response.isSuccessful()){
                    int listSize = response.body().getMessage().size();

                    for (int i =0 ; i<listSize ; i++){
                        String name = response.body().getMessage().get(i).getName();
                        String description = response.body().getMessage().get(i).getDescription();
                        String oldPhoto = response.body().getMessage().get(i).getPhoto();
                        String newPhoto = oldPhoto.replaceAll("\\\\" , "");
                        String createdAt = response.body().getMessage().get(i).getCreatedAt();
                        myStoriesModel = new MyStoriesModel(newPhoto,name,description,createdAt.substring(0,10), R.drawable.ic_barbell);

                        myStoriesModelList.add(myStoriesModel);
                        //Toast.makeText(myStories.this,String.valueOf( myStoriesModelList.size()), Toast.LENGTH_SHORT).show();

                    }
                    MyStoriesAdapter myStoriesAdapter = new MyStoriesAdapter(myStoriesModelList);
                    myStoriesRecycler.setAdapter(myStoriesAdapter);
                }else {
                    Toast.makeText(myStories.this, "SignUpResponse Failed", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<MyStoriesResponse> call, Throwable t) {
                Toast.makeText(myStories.this, "Check Your Internet", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
}