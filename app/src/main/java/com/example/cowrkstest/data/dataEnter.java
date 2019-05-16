package com.example.cowrkstest.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class dataEnter {

    @SerializedName("data")
    ArrayList<post> posts;

    public ArrayList<post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<post> posts) {
        this.posts = posts;
    }
}
