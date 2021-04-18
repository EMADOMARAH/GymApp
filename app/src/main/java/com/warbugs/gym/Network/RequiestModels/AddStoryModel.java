package com.warbugs.gym.Network.RequiestModels;

public class AddStoryModel {
    String description;
    String photo ;

    public AddStoryModel(String description, String photo) {
        this.description = description;
        this.photo = "data:image/jpeg;base64,"+photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
