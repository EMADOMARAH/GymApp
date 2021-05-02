package com.warbugs.gym.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.warbugs.gym.Models.MyStoriesModel;
import com.warbugs.gym.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyStoriesAdapter extends RecyclerView.Adapter<MyStoriesAdapter.MyStoriesViewHolder>   {

    private ArrayList<MyStoriesModel> storiesFeedList;

    public MyStoriesAdapter(ArrayList<MyStoriesModel> storiesFeedList) {
        this.storiesFeedList = storiesFeedList;
    }

    @NonNull
    @Override
    public MyStoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyStoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item_img,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyStoriesViewHolder holder, int position) {
        MyStoriesModel myStoriesModel = storiesFeedList.get(position);

        holder.name.setText(myStoriesModel.getName());
        holder.created_at.setText(myStoriesModel.getCreated_at());
        holder.description.setText(myStoriesModel.getDescription());
        holder.img.setImageResource(myStoriesModel.getImg());

        Picasso.get()
                .load(myStoriesModel.getPhoto())
                .placeholder(R.drawable.placeholder)
                .into(holder.photo);

        //holder.photo.setImageResource(myStoriesModel.getPhoto());


    }



    @Override
    public int getItemCount() {
        return storiesFeedList.size();
    }

    public void setList(ArrayList<MyStoriesModel> myStoriesFeedList){
        this.storiesFeedList = myStoriesFeedList;
        notifyDataSetChanged();
    }


    public class MyStoriesViewHolder extends RecyclerView.ViewHolder{

        ImageView photo;
        CircleImageView img;
        TextView name;
        TextView description;
        TextView created_at;

        public MyStoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.profile_image);
            name  = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.post);
            created_at = itemView.findViewById(R.id.time);
            photo  = itemView.findViewById(R.id.imgitems);
        }
    }

}
