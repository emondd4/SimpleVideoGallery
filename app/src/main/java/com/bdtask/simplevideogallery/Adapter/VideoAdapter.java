package com.bdtask.simplevideogallery.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.simplevideogallery.Model.VideoModel;
import com.bdtask.simplevideogallery.R;
import com.bdtask.simplevideogallery.VideoPlayerActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    ArrayList<VideoModel> videoModels;
    Activity activity;

    public VideoAdapter(Context context, ArrayList<VideoModel> videoModels, Activity activity) {
        this.context = context;
        this.videoModels = videoModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {
        Glide.with(context).load("file://" + videoModels.get(position).getThumb())
                .skipMemoryCache(false)
                .into(holder.imageView);

        holder.select.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.select.setAlpha(0);

        holder.select.setOnClickListener( v -> {

            Intent i = new Intent(context, VideoPlayerActivity.class);
            i.putExtra("Video",videoModels.get(position).getPath());
            activity.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView imageView;
        RelativeLayout select;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            select = itemView.findViewById(R.id.select);
        }
    }

}


