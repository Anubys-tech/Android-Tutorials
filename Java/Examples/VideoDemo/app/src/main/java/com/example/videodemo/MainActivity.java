package com.example.videodemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 18.02.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.videoView)
    VideoView videoView;

    private Unbinder unbinder;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        startVideoPath();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TAG - MainActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void startVideoPath() {
        Log.d(TAG, "TAG - MainActivity - startVideoPath()");

        if (videoView != null) {
            videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
            mediaController();
            videoView.start();
        }
    }

    private void mediaController() {
        Log.d(TAG, "TAG - MainActivity - startVideoPath()");

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        if (videoView != null) {
            videoView.setMediaController(mediaController);
        }
    }
}
