package com.example.retrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;
import com.example.retrofit.model.Comment;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class CommentAdapt extends RecyclerView.Adapter<CommentAdapt.MyViewHolder> implements Serializable {

    private final Context context;
    private List<Comment> commentsitems;

    public CommentAdapt(Context context, List<Comment> commentsitems)
    {
        this.context = context;
        this.commentsitems = commentsitems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtTitle;
        TextView txtBody;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txt_idItem);
            txtTitle = itemView.findViewById(R.id.txt_Title);
            txtBody = itemView.findViewById(R.id.txt_itemBody);
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public CommentAdapt.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CommentAdapt.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CommentAdapt.MyViewHolder holder, int position) {
        Comment element = commentsitems.get(position);
        holder.txtId.setText(String.valueOf(element.getId()));
        holder.txtTitle.setText(element.getName());
        holder.txtBody.setText(element.getBody());
    }

    @Override
    public int getItemCount() {
        return commentsitems.size();
    }

}
