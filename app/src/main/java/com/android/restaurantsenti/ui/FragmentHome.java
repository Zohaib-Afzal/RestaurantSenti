package com.android.restaurantsenti.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.restaurantsenti.R;
import com.android.restaurantsenti.adapter.RecyclerViewAdapterNewlyAddedRestaurants;
import com.android.restaurantsenti.adapter.RecyclerViewAdapterTopRatedRestaurants;
import com.android.restaurantsenti.adapter.RecyclerViewAdapterTopRatedReviews;
import com.android.restaurantsenti.model.Restaurant;
import com.android.restaurantsenti.model.Reviews;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    RecyclerView recyclerViewNewlyAddedRestaurants;
    RecyclerView recyclerViewTopRatedRestaurants;
    RecyclerView recyclerViewTopRatedReviews;
    RecyclerViewAdapterNewlyAddedRestaurants recyclerViewAdapterNewlyAddedRestaurants;
    RecyclerViewAdapterTopRatedRestaurants recyclerViewAdapterTopRatedRestaurants;
    RecyclerViewAdapterTopRatedReviews recyclerViewAdapterTopRatedReviews;
    ArrayList<Restaurant> restaurantList = new ArrayList<>();
    ArrayList<Reviews> reviewsList = new ArrayList<>();
    Restaurant restaurant = new Restaurant();
    Reviews reviews = new Reviews();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findViews(view);
        //setting size of Recycler View
        recyclerViewNewlyAddedRestaurants.setHasFixedSize(true);
        recyclerViewTopRatedReviews.setHasFixedSize(true);
        recyclerViewTopRatedRestaurants.setHasFixedSize(true);
        //setting Layout Manager
        recyclerViewNewlyAddedRestaurants.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewTopRatedReviews.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTopRatedRestaurants.setLayoutManager(new LinearLayoutManager(getActivity()));
        //initiating Adapter
        recyclerViewAdapterNewlyAddedRestaurants = new RecyclerViewAdapterNewlyAddedRestaurants(getActivity(), restaurant.populateDummyData());
        recyclerViewAdapterTopRatedRestaurants = new RecyclerViewAdapterTopRatedRestaurants(getActivity(), restaurant.populateDummyData());
        recyclerViewAdapterTopRatedReviews = new RecyclerViewAdapterTopRatedReviews(getActivity(),reviews.populateDummyData());

        //setting Adapter
        recyclerViewNewlyAddedRestaurants.setAdapter(recyclerViewAdapterNewlyAddedRestaurants);
        recyclerViewTopRatedReviews.setAdapter(recyclerViewAdapterTopRatedRestaurants);
        recyclerViewTopRatedRestaurants.setAdapter(recyclerViewAdapterTopRatedReviews);

        return view;
    }
    private void findViews(View view){
        recyclerViewNewlyAddedRestaurants = view.findViewById(R.id.recycler_view_newly_added_restaurants);
        recyclerViewTopRatedRestaurants = view.findViewById(R.id.recycler_view_top_rated_restaurants);
        recyclerViewTopRatedReviews = view.findViewById(R.id.recycler_view_top_rated_reviews);
    }
}