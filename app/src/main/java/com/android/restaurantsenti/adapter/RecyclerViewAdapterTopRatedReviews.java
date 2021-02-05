package com.android.restaurantsenti.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.restaurantsenti.R;
import com.android.restaurantsenti.model.Reviews;

import java.util.ArrayList;
import java.util.List;
public class RecyclerViewAdapterTopRatedReviews extends RecyclerView.Adapter<RecyclerViewAdapterTopRatedReviews.ViewHolder> {

    private Context context;
    private ArrayList<Reviews> reviewsArrayList;

    public RecyclerViewAdapterTopRatedReviews(Context context, ArrayList<Reviews>  list) {
        this.context = context;
        this.reviewsArrayList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate( R.layout.custome_item_top_rated_reviews,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Reviews contact= reviewsArrayList.get(position);//each contact object inside of our list
        holder.name.setText(contact.getName());
        holder.comments.setText(contact.getComment());


    }
    @Override
    public int getItemCount() {
        return reviewsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView comments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            comments=itemView.findViewById(R.id.comments);
        }
    }
}
