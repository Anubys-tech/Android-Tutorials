package com.anubys.example.downloadwebcontentdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/** @Autor Created by Anubys on the 19.03.2020 */


public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = ImageDownloader.class.getSimpleName();

    @Override
    protected Bitmap doInBackground(String... urls) {
        Log.d(TAG, "TAG - ImageDownloader - doInBackground()");
        URL url;
        HttpsURLConnection urlConnection;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            return (BitmapFactory.decodeStream(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
            return (null);
        }
    }
}
