package com.example.guessvip;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** @Autor Created by Anubys on the 20.03.2020 */


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
}
