package com.android.restaurantsenti.model;

import com.android.restaurantsenti.R;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String location;
    private int images;

    public Restaurant(String name, String location, int images) {
        this.name = name;
        this.location = location;
        this.images = images;
    }

    public Restaurant() {
    }

    public ArrayList<Restaurant> populateDummyData(){
        ArrayList<Restaurant> restaurantArrayList = new ArrayList<>();
        restaurantArrayList.add(new Restaurant("Dominoes", "Islamabad", R.drawable.restaurant1));
        restaurantArrayList.add(new Restaurant("KFC", "Islamabad", R.drawable.restaurant2));
        restaurantArrayList.add(new Restaurant("Peprica", "Islamabad", R.drawable.restaurant3));
        restaurantArrayList.add(new Restaurant("Burger King", "Islamabad", R.drawable.restaurant4));
        restaurantArrayList.add(new Restaurant("Pizza Hut", "Islamabad", R.drawable.restaurant5));
        return restaurantArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
