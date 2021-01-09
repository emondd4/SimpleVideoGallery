package com.bdtask.simplevideogallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;

import java.io.File;

public class VideoPlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        init();

    }

    private void init() {

        playerView = findViewById(R.id.exoplayerView);


        Intent intent = getIntent();
        link = intent.getStringExtra("Video");

        player = new SimpleExoPlayer.Builder(VideoPlayerActivity.this).build();
        playerView.setPlayer(player);
        System.out.println(link);

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                player.setThrowsWhenUsingWrongThread(false);
                MediaItem mediaItem = MediaItem.fromUri(Uri.fromFile(new File(link)));
                player.setMediaItem(mediaItem);
                player.prepare();
                player.play();
            }
        });




    }
}