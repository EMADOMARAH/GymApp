package com.warbugs.gym.UI.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.warbugs.gym.Adapters.HomeAdapter;
import com.warbugs.gym.Models.AllStoriesModel;
import com.warbugs.gym.Network.ApiInterface;
import com.warbugs.gym.Network.ResponseModels.AllSoriesResponse.AllStoriesResponse;
import com.warbugs.gym.Network.ResponseModels.AllStoriesResponseweb.Example;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.addStory.addStory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class home_fragment extends Fragment {
    RecyclerView homeRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<AllStoriesModel> allStoriesModelArrayList;
    FloatingActionButton fab;

    ApiInterface apiInterface;
    Retrofit retrofit;
    AllStoriesModel allStoriesModel;
    SharedPreferences preferences;
    String TOKEN;
    ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);


        progressBar = view.findViewById(R.id.progress_loader);


        preferences = getActivity().getSharedPreferences("GYM_APP", Context.MODE_PRIVATE);
        TOKEN = preferences.getString("TOKEN", "");

        homeRecycler = view.findViewById(R.id.feedRecycler);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        homeRecycler.setLayoutManager(layoutManager);
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), addStory.class);
                startActivity(intent);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://fit-way.mohameek-eg.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        allStoriesModelArrayList = new ArrayList<>();
        getAllStories();
//        homeModelList.add(new HomeModel(R.drawable.pic2, "Emad", "2 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.bk, "Emelya Clark", "1 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic1, "Elz3ama", "5 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic3, "Emad tany", "4 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic4, "Emad talet", "6 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));


        return view;
    }

    private void getAllStories() {
        apiInterface = retrofit.create(ApiInterface.class);

        Call<AllStoriesResponse> getAllStories = apiInterface.getAllStories("Bearer " + TOKEN);
        getAllStories.enqueue(new Callback<AllStoriesResponse>() {
            @Override
            public void onResponse(Call<AllStoriesResponse> call, Response<AllStoriesResponse> response) {
                if (response.isSuccessful()) {
                    int listSize = response.body().getMessage().getData().size();

                    for (int i = 0; i < listSize; i++) {
                        String name = response.body().getMessage().getData().get(i).getName();
                        String description = response.body().getMessage().getData().get(i).getDescription();
                        String oldPhoto = response.body().getMessage().getData().get(i).getPhoto();
                        String newPhoto = oldPhoto.replaceAll("\\\\", "");
                        String createdAt = response.body().getMessage().getData().get(i).getCreatedAt();
                        allStoriesModel = new AllStoriesModel( name, description, createdAt.substring(0, 10),newPhoto, R.drawable.ic_barbell);

                        allStoriesModelArrayList.add(allStoriesModel);
                        //Toast.makeText(myStories.this,String.valueOf( myStoriesModelList.size()), Toast.LENGTH_SHORT).show();
                    }
                    HomeAdapter homeAdapter = new HomeAdapter(allStoriesModelArrayList);
                    homeRecycler.setAdapter(homeAdapter);
                } else {
                    System.out.println(response.errorBody().toString());
                    Toast.makeText(getActivity().getApplicationContext(), "Response Failed", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<AllStoriesResponse> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Error Getting Data", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);

            }
        });

    }


}