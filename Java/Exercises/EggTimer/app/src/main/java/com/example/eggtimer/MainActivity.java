package com.example.eggtimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 13.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.sb_timer)
    SeekBar seekBarTimer;
    @Nullable
    @BindView(R.id.iv_egg)
    ImageView imageViewEgg;
    @Nullable
    @BindView(R.id.tv_countdowntimer)
    TextView textViewCountDownTimer;
    @Nullable
    @BindView(R.id.btn_go)
    Button buttonGo;

    private Unbinder unbinder;
    private Boolean counterIsActive = false;
    private CountDownTimer countDownTimer;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "TAG - MainActivity - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        initSeekBar();
        setListener();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "TAG - MainActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void initSeekBar() {
        Log.d(TAG, "TAG - MainActivity - initSeekBar()");
        if (seekBarTimer != null) {
            seekBarTimer.setMax(600);
            seekBarTimer.setProgress(30);
            seekBarTimer.setOnSeekBarChangeListener(new onSeekBarChangeListenerTimer());
        }
    }

    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        if (buttonGo != null) {
            buttonGo.setOnClickListener( view -> startCountDownTimer());
        }
    }

    private void startCountDownTimer() {
        Log.d(TAG, "TAG - MainActivity - startCountDownTimer()");
        if (counterIsActive) {
            resetTimer();
        } else {
            counterIsActive = true;

            if (buttonGo != null) {
                buttonGo.setText(getResources().getString(R.string.btn_stop));
            }

            if (seekBarTimer != null) {
                seekBarTimer.setEnabled(false);
                countDownTimer = new CountDownTimer(seekBarTimer.getProgress() * 1000 + 100, 1000) {
                    @Override
                    public void onTick(long l) {
                        updateTimer((int) l / 1000);
                    }

                    @Override
                    public void onFinish() {
                        MediaPlayer myMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                        myMediaPlayer.start();
                        resetTimer();
                    }
                }.start();
            }
        }
    }

    private void updateTimer(int secondsLeft) {
        Log.d(TAG, "TAG - MainActivity - updateTimer()");
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = getResources().getString(R.string.txt_null) + secondString;
        }

        if (textViewCountDownTimer != null) {
            String text = minutes + ":" + secondString;
            textViewCountDownTimer.setText(text);
        }
    }

    public void resetTimer() {
        Log.d(TAG, "TAG - MainActivity - resetTimer()");
        countDownTimer.cancel();
        counterIsActive = false;

        if (textViewCountDownTimer != null) {
            textViewCountDownTimer.setText(getResources().getString(R.string.txt_timer));
        }

        if (seekBarTimer != null) {
            seekBarTimer.setProgress(30);
            seekBarTimer.setEnabled(true);
        }

        if (buttonGo != null) {
            buttonGo.setText(getResources().getString(R.string.btn_go));
        }
    }


    //* ************************************************ *
    //*                  C L A S S E S                   *
    //* ************************************************ *
    private class onSeekBarChangeListenerTimer implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateTimer(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
