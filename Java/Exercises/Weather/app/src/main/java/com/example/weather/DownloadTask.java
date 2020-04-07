package com.example.weather;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** @Autor Created by Anubys on the 22.03.2020 */


public class DownloadTask extends AsyncTask<String, Void, String> {
    private static final String TAG = DownloadTask.class.getSimpleName();
    private String temperature;
    private int visibility;
    private MainActivity activity;
    private Resources resources;
    private TextView textViewResult;

    public DownloadTask(MainActivity mainActivity, Resources resources, TextView textViewResult) {
        this.activity = mainActivity;
        this.resources = resources;
        this.textViewResult = textViewResult;
    }


    @Override
    protected String doInBackground(String... urls) {
        Log.d(TAG, "TAG - DownloadTask - doInBackground()");

        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();
            while (data != -1) {
                char currentChar = (char) data;
                result.append(currentChar);
                data = inputStreamReader.read();
            }

            return String.valueOf((result));
        } catch (Exception e) {
            e.printStackTrace();

            return (resources.getString(R.string.text_fail));
        }
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "TAG - DownloadTask - onPostExecute()");
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            Log.i(TAG, "jsonObject: " + jsonObject.toString());

            String weatherInfo = jsonObject.getString("current");
            Log.i(TAG, "WeatherINFO: " + weatherInfo);

            JSONObject currentJSONObject = new JSONObject(weatherInfo);
            String temperatureInC = currentJSONObject.getString("temp_c") + resources.getString(R.string.text_grad);

            setTemperature(temperatureInC);
        } catch (Exception e) {
            e.printStackTrace();
            setVisibility();
        }
    }

    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private void setTemperature(String temperatureInC) {
        Log.d(TAG, "TAG - DownloadTask - setTemperature()");
        if (!temperature.equals("")) {
            textViewResult.setText(temperatureInC);
        } else {
            //textViewResult.setText(resources.getString(R.string.text_temperature_could_not_be_found));
            //OR
            Toast.makeText(activity, resources.getString(R.string.text_temperature_could_not_be_found), Toast.LENGTH_SHORT).show();
        }
    }

    private void setVisibility() {
        Log.d(TAG, "TAG - DownloadTask - setVisibility()");
        textViewResult.setVisibility(View.INVISIBLE);
    }
}
