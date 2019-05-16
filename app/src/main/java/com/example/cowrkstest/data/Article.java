package com.example.cowrkstest.data;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("image_loc")
    String imageUrl;

    @SerializedName("city_name")
    String cityName;

    @SerializedName("center_name")
    String centerName;

    @SerializedName("content")
    String content;

    @SerializedName("timelog")
    String time;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
