package com.warbugs.gym.Models;

public class MyStoriesModel {
    String name,description,created_at;
    int photo  , img;

    public MyStoriesModel(int photo, String name, String description, String created_at,int img) {
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
