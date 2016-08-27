package com.discordgamedetectives.sombrachecker;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.VideoView;

public class SplashActivity extends Activity implements MediaPlayer.OnCompletionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        VideoView video = (VideoView) findViewById(R.id.videoView);
        video.setVideoPath("android.resource://com.discordgamedetectives.sombrachecker/raw/" + R.raw.splash);
        video.start();
        video.setOnCompletionListener(this);

    }
    @Override
    public void onCompletion(MediaPlayer mp){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
