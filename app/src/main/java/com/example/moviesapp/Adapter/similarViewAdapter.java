package com.example.moviesapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Models.DetailsModel;
import com.example.moviesapp.R;

import java.util.ArrayList;


public class similarViewAdapter extends RecyclerView.Adapter<similarViewAdapter.similarViewHolder> {

    private ArrayList<DetailsModel> data;
    Context context;

    public similarViewAdapter(ArrayList<DetailsModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public similarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.similar_layout,parent,false);
        return new similarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull similarViewHolder holder, int position) {
        DetailsModel detailsModel = data.get(position);
        holder.similarMovieName.setText(detailsModel.getName());
        String url = "https://image.tmdb.org/t/p/w300";
        url += detailsModel.getImg();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.similarMovieImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class similarViewHolder extends RecyclerView.ViewHolder {

        ImageView similarMovieImage;
        TextView similarMovieName;
        public similarViewHolder(@NonNull View itemView) {
            super(itemView);
            similarMovieName = itemView.findViewById(R.id.similarMovieNameTextView);
            similarMovieImage = itemView.findViewById(R.id.similarMoviePosterImageView);
        }
    }
}
