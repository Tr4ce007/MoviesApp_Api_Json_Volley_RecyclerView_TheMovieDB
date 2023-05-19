package com.example.moviesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.DetailActivity;
import com.example.moviesapp.Models.NowPlayingModel;
import com.example.moviesapp.R;

import java.util.ArrayList;

public class homeViewAdapter extends RecyclerView.Adapter<homeViewAdapter.homeViewHolder> {

    ArrayList<NowPlayingModel> data;
    Context context;


    public homeViewAdapter(ArrayList<NowPlayingModel> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public homeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_layout,parent,false);
        return new homeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull homeViewHolder holder, int position) {
        if(position %2==0)holder.linearLayout.setBackgroundResource(R.color.cube_orange);
        else holder.linearLayout.setBackgroundResource(R.color.cube_pink_red);
        NowPlayingModel temp=data.get(position);
        holder.homeTitleView.setText(temp.getTitle());
        holder.releaseDateView.setText("Released On : " + temp.getRelease_date());
        String uri="https://image.tmdb.org/t/p/w300";
        uri += temp.getPoster_path();
        Glide.with(holder.itemView.getContext()).load(uri).into(holder.homeImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,temp.getId(),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("id",temp.getId());i.putExtra("name",temp.getTitle());
                i.putExtra("poster",temp.getPoster_path());i.putExtra("plot",temp.getOverview());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class homeViewHolder extends RecyclerView.ViewHolder{
        ImageView homeImageView;
        TextView homeTitleView;
        TextView releaseDateView;

        LinearLayout linearLayout;
        public homeViewHolder(@NonNull View itemView) {
            super(itemView);
            homeTitleView = itemView.findViewById(R.id.homeTitleView);
            releaseDateView = itemView.findViewById(R.id.homeReleaseView);
            homeImageView = itemView.findViewById(R.id.homeImageView);
            linearLayout = itemView.findViewById(R.id.color_ChangeLinearLayout);
        }
    }
}
