package com.anubys.example.backgroundservicedemo;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

/** @Autor Created by Anubys on the 17.02.2020 */


public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    public static final String CHANNEL_ID = "exampleServiceChannelID";


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    public void onCreate() {
        Log.d(TAG, "TAG - BaseApplication - onCreate()");
        super.onCreate();

        createNotificationChannel();
    }


    //* ************************************************ *
    //*          H E L P E R - M E T H O D E             *
    //* ************************************************ *
    private void createNotificationChannel() {
        Log.d(TAG, "TAG - BaseApplication - createNotificationChannel()");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, getResources().getString(R.string.txt_example_service_channel), NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }
}