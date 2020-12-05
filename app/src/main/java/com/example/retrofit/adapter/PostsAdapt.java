package com.example.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;
import com.example.retrofit.model.Posts;

import java.io.Serializable;
import java.util.List;

public class PostsAdapt extends RecyclerView.Adapter<PostsAdapt.MyViewHolder> implements Serializable {

    private final Context context;
    private ClickOnRowListener clickOnRowListener;
    private List<Posts> postsitems;

    public PostsAdapt(Context context, List<Posts> postsitems, ClickOnRowListener clickOnRowListener)
    {
        this.context = context;
        this.postsitems = postsitems;
        this.clickOnRowListener = clickOnRowListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtId;
        TextView txtTitle;
        TextView txtBody;
        ClickOnRowListener clickOnRowListener;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView, ClickOnRowListener clickOnRowListener) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_idItem);
            txtTitle = itemView.findViewById(R.id.txt_Title);
            txtBody = itemView.findViewById(R.id.txt_itemBody);
            this.clickOnRowListener = clickOnRowListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickOnRowListener.ClickOnRow(getAdapterPosition());
        }

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public PostsAdapt.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, clickOnRowListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull PostsAdapt.MyViewHolder holder, int position) {
        Posts element = postsitems.get(position);
        holder.txtId.setText(String.valueOf(element.getId()));
        holder.txtTitle.setText(element.getTitle());
        holder.txtBody.setText(element.getBody());
    }

    @Override
    public int getItemCount() {
        return postsitems.size();
    }

    public interface ClickOnRowListener {
        void ClickOnRow(int position);
    }
}
