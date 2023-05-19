package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapter.creditsViewAdapter;
import com.example.moviesapp.Adapter.homeViewAdapter;
import com.example.moviesapp.Adapter.reviewsViewAdapter;
import com.example.moviesapp.Adapter.similarViewAdapter;
import com.example.moviesapp.Models.DetailsModel;
import com.example.moviesapp.Models.NowPlayingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private String movieId, moviePoster, movieName, moviePlot;
    private ImageView moviePosterImageView;
    private TextView moviePlotTextView,movieNameTextView;
    private RecyclerView rViewSimilar, rViewCredits, rViewReviews;

    private ArrayList<DetailsModel> creditDAta,reviewData,similarData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        creditDAta= new ArrayList<>();
        reviewData=new ArrayList<>();
        similarData=new ArrayList<>();

        moviePosterImageView = findViewById(R.id.detailsImageView);
        moviePlotTextView = findViewById(R.id.detailOverView);
        movieNameTextView = findViewById(R.id.detailMovieName);

        Intent intent = getIntent();
        movieId= intent.getStringExtra("id");
        movieName = intent.getStringExtra("name");
        moviePoster = intent.getStringExtra("poster");
        moviePlot = intent.getStringExtra("plot");

        moviePlotTextView.setText(moviePlot);
        movieNameTextView.setText(movieName);

        String url = "https://image.tmdb.org/t/p/w300";
        url += moviePoster;
        Glide.with(this).load(url).into(moviePosterImageView);

        rViewSimilar = findViewById(R.id.rViewSimilar);
        rViewCredits = findViewById(R.id.rViewCredits);
        rViewReviews=findViewById(R.id.rViewReviews);

        rViewCredits.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rViewReviews.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        rViewSimilar.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));


        fetchCreditsData();
        fetchReviewData();
        fetchSimilarData();
    }
    public void fetchCreditsData(){
        String url="https://api.themoviedb.org/3/movie/";url+=movieId;url+="/credits?api_key=c3615c272afa7c2a0c196b5a9bcda8bb";
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray homeJsonArray = response.getJSONArray("cast");
                            for(int i=0;i<homeJsonArray.length();i++){
                                JSONObject temp =homeJsonArray.getJSONObject(i);
                                Log.d("idea1",temp.getString("id"));
                                DetailsModel hm = new DetailsModel(temp.getString("name"),temp.getString("profile_path"));
                                // ********* Fetch Corresponding Details            *********
                                creditDAta.add(hm);
//                                Log.d("idea1", hm.getId());

                            }
                            // Add adapter to recycler view.
                            rViewCredits.setAdapter(new creditsViewAdapter(creditDAta,getApplicationContext()));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"something not right fetching credits",Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest2);
    }

    public void fetchReviewData(){
        String url="https://api.themoviedb.org/3/movie/";url+=movieId;url+="/reviews?api_key=c3615c272afa7c2a0c196b5a9bcda8bb";
        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray homeJsonArray = response.getJSONArray("results");
                            for(int i=0;i<homeJsonArray.length();i++){
                                JSONObject temp =homeJsonArray.getJSONObject(i);
                                Log.d("idea1",temp.getString("id"));
                                DetailsModel hm = new DetailsModel(temp.getString("author"),temp.getString("content"));
                                // ********* Fetch Corresponding Details            *********
                                reviewData.add(hm);
//                                Log.d("idea1", hm.getId());

                            }
                            // Add adapter to recycler view.
                            rViewReviews.setAdapter(new reviewsViewAdapter(reviewData,getApplicationContext()));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"something not right fetching reviews",Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest3);
    }
    public void fetchSimilarData(){
        String url="https://api.themoviedb.org/3/movie/";url+=movieId;url+="/similar?api_key=c3615c272afa7c2a0c196b5a9bcda8bb";
        JsonObjectRequest jsonObjectRequest4 = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray homeJsonArray = response.getJSONArray("results");
                            for(int i=0;i<homeJsonArray.length();i++){
                                JSONObject temp =homeJsonArray.getJSONObject(i);
                                Log.d("idea1",temp.getString("id"));
                                DetailsModel hm = new DetailsModel(temp.getString("original_title"),temp.getString("poster_path"));
                                // ********* Fetch Corresponding Details            *********
                                similarData.add(hm);
//                                Log.d("idea1", hm.getId());

                            }
                            // Add adapter to recycler view.
                            rViewSimilar.setAdapter(new similarViewAdapter(similarData,getApplicationContext()));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"something not right fetching similars",Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest4);
    }
}