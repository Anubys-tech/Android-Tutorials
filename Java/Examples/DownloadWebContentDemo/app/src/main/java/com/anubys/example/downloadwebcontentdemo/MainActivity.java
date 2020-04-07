package com.anubys.example.downloadwebcontentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/** @Autor Created by Anubys on the 19.03.2020 */


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Nullable
    @BindView(R.id.iv_pic)
    ImageView imageViewPicture;
    @Nullable
    @BindView(R.id.btn_show)
    Button buttonShow;

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

        DownloadTask downloadTask = new DownloadTask(getResources());
        String result;

        try {
            result = downloadTask.execute("https://www.ft-hd-kirchheim.de/").get();
            Log.d("Result", result); //TODO Nicht in einem Log anzeigen, sondern in der Activity (z.B. in einer Liste) beim drücken von Button
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

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

        if (buttonShow != null) {
            buttonShow.setOnClickListener( view -> showPicture());
        }
    }

    private void showPicture() {
        Log.d(TAG, "TAG - MainActivity - showPicture()");
        ImageDownloader imageDownloader = new ImageDownloader();

        if (imageViewPicture != null) {
            try {
                imageViewPicture.setImageBitmap(imageDownloader.execute("https://tmssl.akamaized.net//images/wappen/head/49472.png?lm=1437426409").get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
