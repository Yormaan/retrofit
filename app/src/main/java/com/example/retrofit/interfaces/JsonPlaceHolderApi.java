package com.example.retrofit.interfaces;

import com.example.retrofit.model.Comment;
import com.example.retrofit.model.Posts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Posts>> getPosts();

    @GET("posts/{postId}/comments")
    Call<List<Comment>> getCommentsFromPost(@Path("postId") int postId);
}
