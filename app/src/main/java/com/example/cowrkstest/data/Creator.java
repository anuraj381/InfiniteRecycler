package com.example.cowrkstest.data;

import com.google.gson.annotations.SerializedName;

public class Creator {

    @SerializedName("name")
    String name;

    @SerializedName("image_loc")
    String imageUrl;

    @SerializedName("city_name")
    String cityName;

    @SerializedName("center_name")
    String centerName;

    @SerializedName("company")
    String company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
