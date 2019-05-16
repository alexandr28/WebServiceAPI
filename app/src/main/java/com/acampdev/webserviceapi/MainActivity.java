package com.acampdev.webserviceapi;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.acampdev.webserviceapi.Adapters.FilmsAdapter;
import com.acampdev.webserviceapi.Models.Film;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Film> filmList= new ArrayList<>();
    String url;
    FilmsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerV);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        OkHttpClient client= new OkHttpClient();
        Request request= new Request.Builder().url("https://swapi.co/api/films/?format=json").build();
        client.newCall(request).enqueue(new Callback() {
            @EverythingIsNonNull
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(getApplicationContext(),"fail Connection",Toast.LENGTH_SHORT).show();
            }
            @EverythingIsNonNull
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                assert response.body() != null;
                String json= response.body().string();
                JsonArray jsonArray;
                JsonObject jsonObject= new Gson().fromJson(json,JsonObject.class);
                jsonArray= jsonObject.getAsJsonArray("results");
                Gson gson= new  GsonBuilder().create();
                // type reflect -> java land
                Type list= new TypeToken<List<Film>>(){}.getType();
                filmList= gson.fromJson(jsonArray.toString(),list);

                runOnUiThread(() -> {
                    adapter= new FilmsAdapter(filmList,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                });
            }
        });
        update();
    }

    public void update(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter= new FilmsAdapter(filmList,getApplicationContext());
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
