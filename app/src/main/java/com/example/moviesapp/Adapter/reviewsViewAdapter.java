package com.example.moviesapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Models.DetailsModel;
import com.example.moviesapp.R;

import java.util.ArrayList;

public class reviewsViewAdapter extends RecyclerView.Adapter<reviewsViewAdapter.reviewsViewHolder> {
    private ArrayList<DetailsModel> data;
    Context context;

    public reviewsViewAdapter(ArrayList<DetailsModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public reviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.reviews_layout,parent,false);
        return new reviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull reviewsViewHolder holder, int position) {
        DetailsModel detailsModel = data.get(position);
        holder.personName.setText(detailsModel.getName());
        holder.reviewText.setText(detailsModel.getImg());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class reviewsViewHolder extends RecyclerView.ViewHolder {

        TextView personName;
        TextView reviewText;
        public reviewsViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.reviewByTextView);
            reviewText = itemView.findViewById(R.id.reviewsTextView);
        }
    }
}
