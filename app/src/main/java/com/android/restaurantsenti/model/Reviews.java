package com.android.restaurantsenti.model;

import com.android.restaurantsenti.R;

import java.util.ArrayList;

public class Reviews {
    private String name;
    private String comment;

    public Reviews(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Reviews() {
    }

    public ArrayList<Reviews> populateDummyData(){
        ArrayList<Reviews> reviewsArrayList = new ArrayList<>();
        reviewsArrayList.add(new Reviews("ALi", "Good"));
        reviewsArrayList.add(new Reviews("Arqum", "Excellent"));
        reviewsArrayList.add(new Reviews("Afaq", "Very good"));
        reviewsArrayList.add(new Reviews("Zohaib", "Very bad"));
        reviewsArrayList.add(new Reviews("Asad", "bad"));
        return reviewsArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
