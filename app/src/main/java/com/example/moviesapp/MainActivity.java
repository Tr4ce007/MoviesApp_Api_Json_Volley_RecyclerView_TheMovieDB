package com.example.moviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.moviesapp.Adapter.homeViewAdapter;
import com.example.moviesapp.Models.NowPlayingModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rView;
    public ArrayList<NowPlayingModel> data;
    private Button bookNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data=new ArrayList<>();
        rView=findViewById(R.id.rView);
        rView.setLayoutManager(new LinearLayoutManager(this));
        fetchData();

    }

    public void fetchData(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, getString(R.string.Now_Playing), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray homeJsonArray = response.getJSONArray("results");
                            for(int i=0;i<homeJsonArray.length();i++){
                                JSONObject temp =homeJsonArray.getJSONObject(i);
                                Log.d("idea1",temp.getString("id"));
                                NowPlayingModel hm = new NowPlayingModel(temp.getString("id"),temp.getString("title"),
                                        temp.getString("release_date"),temp.getString("poster_path"),
                                        temp.getString("overview"));
                                data.add(hm);
//                                Log.d("idea1", hm.getId());

                            }
                            // Add adapter to recycler view.
                            rView.setAdapter(new homeViewAdapter(data,MainActivity.this));
                            //rView.setAdapter(new homeViewAdapter(data,getApplicationContext()));
                            // This line will cause an error to fix use intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"something not right",Toast.LENGTH_LONG).show();
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}