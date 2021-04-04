package com.warbugs.gym.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.warbugs.gym.Adapters.HomeAdapter;
import com.warbugs.gym.Models.HomeModel;
import com.warbugs.gym.R;
import com.warbugs.gym.UI.addStory.addStory;

import java.util.ArrayList;


public class home_fragment extends Fragment {
    RecyclerView homeRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<HomeModel> homeModelList;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);

        homeRecycler = view.findViewById(R.id.feedRecycler);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        homeRecycler.setLayoutManager(layoutManager);
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , addStory.class );
                startActivity(intent);
            }
        });

        homeModelList = new ArrayList<>();

        homeModelList.add(new HomeModel(R.drawable.pic2, "Emad", "2 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.bk, "Emelya Clark", "1 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic1, "Elz3ama", "5 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic3, "Emad tany", "4 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic4, "Emad talet", "6 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic2, "Emad", "2 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.bk, "Emelya Clark", "1 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic1, "Elz3ama", "5 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic3, "Emad tany", "4 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
//        homeModelList.add(new HomeModel(R.drawable.pic4, "Emad talet", "6 hours ago", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));


        HomeAdapter homeAdapter = new HomeAdapter(homeModelList);
        homeRecycler.setAdapter(homeAdapter);

        return view;
    }





}