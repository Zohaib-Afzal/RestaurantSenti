package com.android.restaurantsenti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.restaurantsenti.R;
import com.android.restaurantsenti.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
public class RecyclerViewAdapterTopRatedRestaurants extends RecyclerView.Adapter<RecyclerViewAdapterTopRatedRestaurants.ViewHolder> {

    private Context context;
    private ArrayList<Restaurant> restaurantArrayList;

    public RecyclerViewAdapterTopRatedRestaurants(Context context, ArrayList<Restaurant> list) {
        this.context = context;
        this.restaurantArrayList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate( R.layout.custome_item_top_rated_restaurants,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Restaurant restaurant= restaurantArrayList.get(position);//each contact object inside of our list
        holder.name.setText(restaurant.getName());
        holder.location.setText(restaurant.getLocation());
        holder.imageView.setImageResource(restaurant.getImages());


    }
    @Override
    public int getItemCount() {
        return restaurantArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView location;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            location=itemView.findViewById(R.id.location);
            imageView = itemView.findViewById(R.id.image);

        }
    }
}
