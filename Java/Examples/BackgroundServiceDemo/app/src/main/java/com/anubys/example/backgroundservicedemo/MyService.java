package com.anubys.example.backgroundservicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.anubys.example.backgroundservicedemo.BaseApplication.CHANNEL_ID;

/** @Autor Created by Anubys on the 17.02.2020 */


public class MyService extends Service {
    private static final String TAG = BaseApplication.class.getSimpleName();

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "TAG - MyService - onCreate()");
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(getResources().getString(R.string.title_example_service))
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        //do heavy work on a background thread
        //stopSelf();

        return (START_NOT_STICKY);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "TAG - MyService - onBind()");
        return (null);
    }
}