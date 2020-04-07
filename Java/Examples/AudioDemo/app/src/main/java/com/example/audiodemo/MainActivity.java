package com.example.audiodemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 18.02.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.btn_start)
    Button buttonStart;
    @Nullable
    @BindView(R.id.btn_pause)
    Button buttonPause;
    @Nullable
    @BindView(R.id.btn_stop)
    Button buttonStop;
    @Nullable
    @BindView(R.id.volumeSeekBar)
    SeekBar volumeSeekBar;
    @Nullable
    @BindView(R.id.timeSeekBar)
    SeekBar timeSeekBar;

    private MediaPlayer mediaPlayer;
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

        createMediaPlayer();
        setListener();
        setAudioManager();
        Timer();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TAG - MainActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        if (buttonStart != null) {
            buttonStart.setOnClickListener(this::startMediaPlayer);
        }

        if (buttonPause != null) {
            buttonPause.setOnClickListener(this::pauseMediaPlayer);
        }

        if (buttonStop != null) {
            buttonStop.setOnClickListener(this::stopMediaPlayer);
        }
    }

    private void setAudioManager() {
        Log.d(TAG, "TAG - MainActivity - setAudioManager()");

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        if (audioManager != null) {
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

            if (volumeSeekBar != null) {
                volumeSeekBar.setMax(maxVolume);
                volumeSeekBar.setProgress(currentVolume);
                volumeSeekBar.setOnSeekBarChangeListener(new onVolumeSeekBarChangeListener(audioManager));
            }

            if (timeSeekBar != null) {
                //timeSeekBar.setMax(mediaPlayer.getDuration()); //TODO warum mediaPlayer null object ???
                timeSeekBar.setOnSeekBarChangeListener(new onTimeSeekBarChangeListener());
            }
        }
    }

    private void createMediaPlayer() {
        Log.d(TAG, "TAG - MainActivity - createMediaPlayer()");
        mediaPlayer = MediaPlayer.create(this, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.musik));
    }

    public void startMediaPlayer(View view) {
        Log.d(TAG, "TAG - MainActivity - startMediaPlayer()");

        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void pauseMediaPlayer(View view) {
        Log.d(TAG, "TAG - MainActivity - pauseMediaPlayer()");

        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stopMediaPlayer(View view) {
        Log.d(TAG, "TAG - MainActivity - stopMediaPlayer()");

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void Timer() {
        Log.d(TAG, "TAG - MainActivity - Timer()");
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeSeekBar != null && mediaPlayer != null) {
                    timeSeekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }
        }, 0, 1000);
    }


    //* ************************************************ *
    //*                  C L A S S E S                   *
    //* ************************************************ *
    // static -> Damit wird auf keine globalen Variablen außerhalb der Klasse zugegriffen. Diese müssen
    // expliziert im Konstruktor übergeben werden.
    private static class onVolumeSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        private AudioManager audioManager;

        onVolumeSeekBarChangeListener(AudioManager audioManager) {
            this.audioManager = audioManager;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.i(TAG, "VolumeSeekBar: " + progress);

            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    // Hier ein Beispiel ohne static vor der class
    private class onTimeSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            Log.i(TAG, "ScrubSeekBar: " + progress);

            mediaPlayer.seekTo(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
