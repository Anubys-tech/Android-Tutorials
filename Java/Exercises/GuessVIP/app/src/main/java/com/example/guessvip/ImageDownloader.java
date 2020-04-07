package com.example.guessvip;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/** @Autor Created by Anubys on the 20.03.2020 */


public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = DownloadTask.class.getSimpleName();

    @Override
    protected Bitmap doInBackground(String... urls) {
        Log.d(TAG, "TAG - ImageDownloader - doInBackground()");

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();

            return (BitmapFactory.decodeStream(inputStream));
        } catch (Exception e) {
            e.printStackTrace();

            return (null);
        }
    }
}
