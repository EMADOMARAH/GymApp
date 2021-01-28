package com.warbugs.gym.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.warbugs.gym.Models.HomeModel;
import com.warbugs.gym.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ArrayList<HomeModel> feedList ;

    public HomeAdapter(ArrayList<HomeModel> feedList) {
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        HomeModel homeModel = feedList.get(position);

        holder.personalImg.setImageResource(homeModel.getPersonalImg());
        holder.name.setText(homeModel.getName());
        holder.post.setText(homeModel.getPost());
        holder.time.setText(homeModel.getTime());



    }

    @Override
    public int getItemCount() {


        return feedList.size();

    }

    public void setlist (ArrayList<HomeModel> feedList){
        this.feedList = feedList;
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView personalImg;
        TextView name;
        TextView post;
        TextView time;
        Button follow;


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            personalImg = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.name);
            post = itemView.findViewById(R.id.post);
            time = itemView.findViewById(R.id.time);
            follow = itemView.findViewById(R.id.follow);
        }
    }
}

