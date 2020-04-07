package com.anubys.example.backgroundservicedemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 17.02.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Nullable
    @BindView(R.id.edit_text_input)
    EditText editTextInput;
    @Nullable
    @BindView(R.id.button_start_service)
    Button buttonStart;
    @Nullable
    @BindView(R.id.button_stop_service)
    Button buttonStop;

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

        setListener();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "TAG - MenuActivity - onDestroy()");
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    //* ************************************************ *
    //*          H E L P E R - M E T H O D E             *
    //* ************************************************ *
    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");
        if (buttonStart != null) {
            buttonStart.setOnClickListener( view -> startService());
        }

        if (buttonStop != null) {
            buttonStop.setOnClickListener( view -> stopService());
        }
    }

    public void startService() {
        Log.d(TAG, "TAG - MainActivity - startService()");
        String input = null;

        if (editTextInput != null) {
            input = editTextInput.getText().toString();
        }

        Intent serviceIntent = new Intent(this, MyService.class);
        serviceIntent.putExtra("inputExtra", input);

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Log.d(TAG, "TAG - MainActivity - stopService()");
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
    }
}
