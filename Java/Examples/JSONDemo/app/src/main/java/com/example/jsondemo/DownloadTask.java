package com.example.jsondemo;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** @Autor Created by Anubys on the 22.03.2020 */


public class DownloadTask extends AsyncTask<String, Void, String> {
    private static final String TAG = DownloadTask.class.getSimpleName();

    private Resources resources;

    DownloadTask(Resources resources) {
        this.resources = resources;
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

            return (resources.getString(R.string.txt_fail));
        }
    }

    @Override
    protected void onPostExecute(String sResult) {
        Log.d(TAG, "TAG - DownloadTask - onPostExecute()");
        super.onPostExecute(sResult);

        try {
            JSONObject jsonObject = new JSONObject(sResult);
            String weatherInfo = jsonObject.getString("weather");
            Log.i(TAG, "onPostExecute: " + weatherInfo);

            JSONArray jsonArray = new JSONArray(weatherInfo);
            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject jsonPart = jsonArray.getJSONObject(i);
                //TODO Das Ergebnis soll in MainActivity in einer ListView angezeigt werden.

                Log.i(TAG, "onPostExecute: " + jsonPart.getString("main"));
                Log.i(TAG, "onPostExecute: " + jsonPart.getString("description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
