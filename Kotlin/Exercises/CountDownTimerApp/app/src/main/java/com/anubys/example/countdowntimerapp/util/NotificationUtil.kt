package com.anubys.example.countdowntimerapp.util

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat

import com.anubys.example.countdowntimerapp.AppConstants
import com.anubys.example.countdowntimerapp.CountDownTimerActivity
import com.anubys.example.countdowntimerapp.R
import com.anubys.example.countdowntimerapp.CountDownTimerNotificationActionReceiver

import java.text.SimpleDateFormat
import java.util.*

/** @Author Created by Anubys on the 09.05.2020 */


class NotificationUtil {
    companion object {
        private const val CHANNEL_ID_TIMER = "CHANNEL_ID_TIMER"
        private const val CHANNEL_NAME_TIMER = "CHANNEL_NAME_TIMER"
        private const val TIMER_ID = 0

        fun showTimerExpired(context: Context) {
            val startIntent = Intent(context, CountDownTimerNotificationActionReceiver::class.java)
            startIntent.action = AppConstants.ACTION_START
            val startPendingIntent = PendingIntent.getBroadcast(context, 0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val nBuilder = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, true)
            nBuilder.setContentTitle("Timer Expired!")
            nBuilder.setContentText("Start again?")
            nBuilder.setContentIntent(getPendingIntentWithStack(context, CountDownTimerActivity::class.java))
            nBuilder.addAction(R.drawable.ic_play_arrow, "Start", startPendingIntent)

            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, true)

            nManager.notify(TIMER_ID, nBuilder.build())
        }

        fun showTimerRunning(context: Context, wakeUpTime: Long) {
            val stopIntent = Intent(context, CountDownTimerNotificationActionReceiver::class.java)
            stopIntent.action = AppConstants.ACTION_STOP
            val stopPendingIntent = PendingIntent.getBroadcast(context, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val pauseIntent = Intent(context, CountDownTimerNotificationActionReceiver::class.java)
            pauseIntent.action = AppConstants.ACTION_PAUSE
            val pausePendingIntent = PendingIntent.getBroadcast(context, 0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val df = SimpleDateFormat.getTimeInstance(SimpleDateFormat.SHORT)

            val nBuilder = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, true)
            nBuilder.setContentTitle("Timer is Running.")
            nBuilder.setContentText("End: ${df.format(Date(wakeUpTime))}")
            nBuilder.setContentIntent(getPendingIntentWithStack(context, CountDownTimerActivity::class.java))
            nBuilder.setOngoing(true)
            nBuilder.addAction(R.drawable.ic_stop, "Stop", stopPendingIntent)
            nBuilder.addAction(R.drawable.ic_pause, "Pause", pausePendingIntent)

            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, true)

            nManager.notify(TIMER_ID, nBuilder.build())
        }

        fun showTimerPaused(context: Context) {
            val resumeIntent = Intent(context, CountDownTimerNotificationActionReceiver::class.java)
            resumeIntent.action = AppConstants.ACTION_RESUME
            val resumePendingIntent = PendingIntent.getBroadcast(context, 0, resumeIntent, PendingIntent.FLAG_UPDATE_CURRENT)

            val nBuilder = getBasicNotificationBuilder(context, CHANNEL_ID_TIMER, true)
            nBuilder.setContentTitle("Timer is paused.")
            nBuilder.setContentText("Resume?")
            nBuilder.setContentIntent(getPendingIntentWithStack(context, CountDownTimerActivity::class.java))
            nBuilder.setOngoing(true)
            nBuilder.addAction(R.drawable.ic_play_arrow, "Resume", resumePendingIntent)

            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.createNotificationChannel(CHANNEL_ID_TIMER, CHANNEL_NAME_TIMER, true)

            nManager.notify(TIMER_ID, nBuilder.build())
        }

        fun hideTimerNotification(context: Context) {
            val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nManager.cancel(TIMER_ID)
        }

        private fun getBasicNotificationBuilder(context: Context, channelId: String, playSound: Boolean) : NotificationCompat.Builder {
            val notificationSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val nBuilder = NotificationCompat.Builder(context, channelId)
            nBuilder.setSmallIcon(R.drawable.ic_timer)
            nBuilder.setAutoCancel(true)
            nBuilder.setDefaults(0)
            if (playSound) nBuilder.setSound(notificationSound)

            return (nBuilder)
        }

        private fun <T> getPendingIntentWithStack(context: Context, javaClass: Class<T>) : PendingIntent {
            val resultIntent = Intent(context, javaClass)
            // Öffne die alte Activity im Stack oder erstelle eine neue Activity
            resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(javaClass)
            stackBuilder.addNextIntent(resultIntent)

            return (stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT))
        }

        @TargetApi(26)
        private fun NotificationManager.createNotificationChannel(channelID: String, channelName: String, playSound: Boolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelImportance = if (playSound) NotificationManager.IMPORTANCE_DEFAULT
                else NotificationManager.IMPORTANCE_LOW
                val nChannel = NotificationChannel(channelID, channelName, channelImportance)
                nChannel.enableLights(true)
                nChannel.lightColor = Color.BLUE
                this.createNotificationChannel(nChannel)
            }
        }
    }
}
