package com.bdtask.simplevideogallery.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.simplevideogallery.R;
import com.bdtask.simplevideogallery.Model.VideoData;
import com.bdtask.simplevideogallery.VideoPlayerActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    Context context;
    ArrayList<VideoData> videoData;
    Activity activity;
    ImageLoader imageLoader;

    public VideoAdapter(Context context, ArrayList<VideoData> videoData, Activity activity, ImageLoader imageLoader) {
        this.context = context;
        this.videoData = videoData;
        this.activity = activity;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout,parent,false);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

        this.imageLoader.displayImage(((VideoData) this.videoData.get(position)).videouri.toString(), holder.imageView, new DisplayImageOptions.Builder().showImageForEmptyUri(0).cacheInMemory(true).showStubImage(R.color.trans).cacheOnDisk(true).resetViewBeforeLoading(true).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.ARGB_8888).delayBeforeLoading(100).postProcessor(new BitmapProcessor() {
            public Bitmap process(Bitmap bitmap) {
                return Bitmap.createScaledBitmap(bitmap, 100, 100, false);
            }
        }).displayer(new SimpleBitmapDisplayer()).build());
        holder.name.setText(videoData.get(position).videoName);
        holder.select.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.select.setAlpha(0);

        holder.select.setOnClickListener( v -> {

            Intent i = new Intent(context, VideoPlayerActivity.class);
            i.putExtra("Video",videoData.get(position).videouri.toString());
            activity.startActivity(i);

        });
    }

    @Override
    public int getItemCount() {
        return videoData.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        RelativeLayout select;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            select = itemView.findViewById(R.id.select);
        }
    }

}


