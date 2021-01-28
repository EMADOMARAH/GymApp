package com.warbugs.gym.UI.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.warbugs.gym.Adapters.HomeAdapter;
import com.warbugs.gym.Models.HomeModel;
import com.warbugs.gym.R;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {

    RecyclerView homeRecycler;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<HomeModel> homeModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeRecycler = findViewById(R.id.feedRecycler);
        layoutManager = new LinearLayoutManager(home.this,RecyclerView.VERTICAL,false);
        homeRecycler.setLayoutManager(layoutManager);

        homeModelList = new ArrayList<>();

        homeModelList.add(new HomeModel(R.drawable.pic2,"Emad","2 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.bk,"Emelya Clark","1 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic1,"Elz3ama","5 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic3,"Emad tany","4 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic4,"Emad talet","6 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic2,"Emad","2 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.bk,"Emelya Clark","1 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic1,"Elz3ama","5 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic3,"Emad tany","4 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        homeModelList.add(new HomeModel(R.drawable.pic4,"Emad talet","6 hours ago","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));


        HomeAdapter homeAdapter = new HomeAdapter(homeModelList);
        homeRecycler.setAdapter(homeAdapter);

    }
}