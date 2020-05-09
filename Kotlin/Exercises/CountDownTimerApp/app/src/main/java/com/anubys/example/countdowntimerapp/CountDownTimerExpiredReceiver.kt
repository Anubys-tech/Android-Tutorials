package com.anubys.example.countdowntimerapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.anubys.example.countdowntimerapp.util.NotificationUtil
import com.anubys.example.countdowntimerapp.util.PreferenceUtil

/** @Autor Created by Anubys on the 07.05.2020 */


class CountDownTimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PreferenceUtil.setTimerState(context, CountDownTimerActivity.TimerState.Stopped)
        PreferenceUtil.setAlarmSetTime(context, 0)
    }
}
