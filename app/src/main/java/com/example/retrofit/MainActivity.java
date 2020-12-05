package com.example.retrofit;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.adapter.PostsAdapt;
import com.example.retrofit.interfaces.JsonPlaceHolderApi;
import com.example.retrofit.model.Posts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PostsAdapt.ClickOnRowListener {

    private RecyclerView recyclerlist;
    private List<Posts> postsitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<Posts>> listCall = retrofit.create(JsonPlaceHolderApi.class).getPosts();

        listCall.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                postsitems = response.body();
                PostsAdapt adapter = new PostsAdapt(getApplicationContext(), postsitems, MainActivity.this);
                recyclerlist = findViewById(R.id.recyclerlist);
                recyclerlist.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerlist.setHasFixedSize(true);
                recyclerlist.setItemAnimator(new DefaultItemAnimator());
                recyclerlist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }

    @Override
    public void ClickOnRow(int position) {
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra("Position", position);
        startActivity(intent);
    }
}
