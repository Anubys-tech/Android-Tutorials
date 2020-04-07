package com.anubys.example.downloadwebcontentdemo;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/** @Autor Created by Anubys on the 19.03.2020 */


public class DownloadTask extends AsyncTask<String, Void, String> {
    private static final String TAG = DownloadTask.class.getSimpleName();

    private Resources resources;


    public DownloadTask(Resources resources) {
        this.resources = resources;
    }

    @Override
    protected String doInBackground(String... urls) {
        Log.d(TAG, "TAG - DownloadTask - doInBackground()");
        StringBuilder result = new StringBuilder();
        URL url;
        HttpURLConnection urlConnection;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int data = inputStreamReader.read();
            while (data != -1) {
                char currentChar = (char) data;
                result.append(currentChar);
                data = inputStreamReader.read();
            }

            return String.valueOf((result));
        } catch (IOException e) {
            e.printStackTrace();
            return (resources.getString(R.string.txt_fail));
        }
    }
}
