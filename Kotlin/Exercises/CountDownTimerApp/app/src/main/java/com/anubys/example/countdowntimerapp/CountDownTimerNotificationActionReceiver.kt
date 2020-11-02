package com.anubys.example.countdowntimerapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.anubys.example.countdowntimerapp.util.NotificationUtil
import com.anubys.example.countdowntimerapp.util.PreferenceUtil

/** @Author Created by Anubys on the 09.05.2020 */


class CountDownTimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action){
            AppConstants.ACTION_STOP -> {
                CountDownTimerActivity.removeAlarm(context)
                PreferenceUtil.setTimerState(context, CountDownTimerActivity.TimerState.Stopped)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PreferenceUtil.getSecondsRemaining(context)
                val alarmSetTime = PreferenceUtil.getAlarmSetTime(context)
                val nowSeconds = CountDownTimerActivity.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PreferenceUtil.setSecondsRemaining(context, secondsRemaining)

                CountDownTimerActivity.removeAlarm(context)
                PreferenceUtil.setTimerState(context, CountDownTimerActivity.TimerState.Paused)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PreferenceUtil.getSecondsRemaining(context)
                val wakeUpTime = CountDownTimerActivity.setAlarm(context, CountDownTimerActivity.nowSeconds, secondsRemaining)
                PreferenceUtil.setTimerState(context, CountDownTimerActivity.TimerState.Running)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PreferenceUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = CountDownTimerActivity.setAlarm(context, CountDownTimerActivity.nowSeconds, secondsRemaining)
                PreferenceUtil.setTimerState(context, CountDownTimerActivity.TimerState.Running)
                PreferenceUtil.setSecondsRemaining(context, secondsRemaining)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}
