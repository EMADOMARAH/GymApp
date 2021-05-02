package com.warbugs.gym.Models;

public class AllStoriesModel {
    String name,description,created_at , photo;
    int img;

    public AllStoriesModel(String name, String description, String created_at, String photo, int img) {
        this.name = name;
        this.description = description;
        this.created_at = created_at;
        this.photo = photo;
        this.img = img;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
