package com.warbugs.gym.Models;

public class HomeModel
{
    int personalImg ;
    String name , time, post;
   // boolean follow;

    public HomeModel(int personalImg, String name, String time, String post) {
        this.personalImg = personalImg;
        this.name = name;
        this.time = time;
        this.post = post;

    }

    public int getPersonalImg() {
        return personalImg;
    }

    public void setPersonalImg(int personalImg) {
        this.personalImg = personalImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}
