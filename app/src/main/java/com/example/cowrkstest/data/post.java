package com.example.cowrkstest.data;

import com.google.gson.annotations.SerializedName;

public class post {

    @SerializedName("article")
    Article article;

    @SerializedName("creator")
    Creator creator;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }
}
