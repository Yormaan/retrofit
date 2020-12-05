package com.example.retrofit;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.adapter.CommentAdapt;
import com.example.retrofit.interfaces.JsonPlaceHolderApi;
import com.example.retrofit.model.Comment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private RecyclerView recyclerlist;
    private List<Comment> commentsitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);


        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<List<Comment>> listCall = retrofit.create(JsonPlaceHolderApi.class).getCommentsFromPost(getIntent().getExtras().getInt("Position") + 1);

        listCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                commentsitems = response.body();
                assert commentsitems != null;
                for (Comment comment: commentsitems
                     ) {
                    System.out.println("Commentario: " + comment.getId());
                }
                CommentAdapt adapter = new CommentAdapt(getApplicationContext(), commentsitems);
                recyclerlist = findViewById(R.id.recycler_listaComent);
                recyclerlist.setLayoutManager(new LinearLayoutManager(CommentsActivity.this));
                recyclerlist.setHasFixedSize(true);
                recyclerlist.setItemAnimator(new DefaultItemAnimator());
                recyclerlist.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });
    }
}