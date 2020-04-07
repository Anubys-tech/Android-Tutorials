package com.example.weather;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 22.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.et_input)
    EditText editTextInput;
    @Nullable
    @BindView(R.id.tv_result)
    TextView textViewResult;
    @Nullable
    @BindView(R.id.btn_getTemperature)
    Button buttonTemperature;

    private Unbinder unbinder;


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unbinder = ButterKnife.bind(this);

        setListener();
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
    private void setListener() {
        Log.d(TAG, "TAG - MainActivity - setListener()");

        if (buttonTemperature != null) {
            buttonTemperature.setOnClickListener(view -> getTemperatur());
        }
    }

    private void getTemperatur() {
        Log.d(TAG, "TAG - MainActivity - getTemperatur()");
        try {
            DownloadTask task = new DownloadTask(this, getResources(), textViewResult);
            if (editTextInput != null) {
                // TODO Eine kostenlose API suchen, welche hier verwendet werden kann. Diese ist nicht mehr gültig
                String cityName = URLEncoder.encode(editTextInput.getText().toString(), "UTF-8");
                // TODO Die API ist veraltet, muss angepasst werden oder eine neue kostenlose API besorgt weren
                // TODO Desweiteren, muss das JSONObject mit Attributen gegebenenfalls neu angepasst werden.
                task.execute("http://api.apixu.com/v1/current.json?key=bf41079f1ce149808cf112153181701&q=" + cityName);
            }

            if (textViewResult != null) {
                textViewResult.setVisibility(View.VISIBLE);
            }

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(editTextInput.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
