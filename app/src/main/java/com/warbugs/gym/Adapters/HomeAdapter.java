package com.warbugs.gym.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.warbugs.gym.Models.AllStoriesModel;
import com.warbugs.gym.Models.HomeModel;
import com.warbugs.gym.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private ArrayList<AllStoriesModel> feedList ;

    public HomeAdapter(ArrayList<AllStoriesModel> feedList) {
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_img,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        AllStoriesModel allStoriesModel = feedList.get(position);

        holder.personalImg.setImageResource(allStoriesModel.getImg());
        holder.name.setText(allStoriesModel.getName());
        holder.post.setText(allStoriesModel.getDescription());
        holder.time.setText(allStoriesModel.getCreated_at());
        Picasso.get()
                .load(allStoriesModel.getPhoto())
                .placeholder(R.drawable.placeholder)
                .into(holder.photo);

    }

    @Override
    public int getItemCount() {


        return feedList.size();

    }

    public void setlist (ArrayList<AllStoriesModel> feedList){
        this.feedList = feedList;
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        CircleImageView personalImg;
        TextView name;
        TextView post;
        TextView time;
        Button follow;
        ImageView photo;



        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            personalImg = itemView.findViewById(R.id.profile_image);
            name = itemView.findViewById(R.id.name);
            post = itemView.findViewById(R.id.post);
            time = itemView.findViewById(R.id.time);
            follow = itemView.findViewById(R.id.follow_btn);
            photo  = itemView.findViewById(R.id.imgitems);

            follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (follow.getText().toString()){
                        case "Follow":
                            follow.setText("Unfollow");
                            break;
                        case "Unfollow":
                            follow.setText("Follow");
                            break;
                    }
                }
            });
        }
    }
}

