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

public class creditsViewAdapter extends RecyclerView.Adapter<creditsViewAdapter.creditsViewHolder> {

    private ArrayList<DetailsModel> data;
    Context context;

    public creditsViewAdapter(ArrayList<DetailsModel> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public creditsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(com.example.moviesapp.R.layout.credits_layout,parent,false);
        return new creditsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull creditsViewHolder holder, int position) {
        DetailsModel detailsModel = data.get(position);
        holder.celebrityName.setText(detailsModel.getName());
        String url = "https://image.tmdb.org/t/p/w300";
        url += detailsModel.getImg();
        Glide.with(holder.itemView.getContext()).load(url).into(holder.celebrityImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class creditsViewHolder extends RecyclerView.ViewHolder {

        ImageView celebrityImage;
        TextView celebrityName;
        public creditsViewHolder(@NonNull View itemView) {
            super(itemView);

            celebrityImage = itemView.findViewById(R.id.creditsImageView);
            celebrityName = itemView.findViewById(R.id.creditsTextView);
        }
    }
}
