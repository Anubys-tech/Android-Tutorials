package com.anubys.example.countdowntimerapp.util

import android.content.Context
import android.preference.PreferenceManager
import com.anubys.example.countdowntimerapp.CountDownTimerActivity

/** @Author Created by Anubys on the 07.05.2020 */


class PreferenceUtil {
    companion object {
        private const val TIMER_LENGTH_ID = "TIMER_LENGTH_ID"
        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "PREVIOUS_TIMER_LENGTH_SECONDS_ID"
        private const val TIMER_STATE_ID = "TIMER_STATE_ID"
        private const val SECONDS_REMAINING_ID = "SECONDS_REMAINING_ID"
        private const val ALARM_SET_TIME_ID = "ALARM_SET_TIME_ID"

        /**
         * Diese Funktion setzt den Default-Timer-Wert auf 5 Minuten.
         */
        fun getTimerLength(context: Context): Int{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(TIMER_LENGTH_ID, 5)
        }

        fun getPreviousTimerLengthSeconds(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return (preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0))
        }

        fun setPreviousTimerLengthSeconds(context: Context, seconds: Long){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        fun getTimerState(context: Context): CountDownTimerActivity.TimerState{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return CountDownTimerActivity.TimerState.values()[ordinal]
        }

        fun setTimerState(context: Context, state: CountDownTimerActivity.TimerState){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        fun getSecondsRemaining(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(context: Context, seconds: Long){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

        fun getAlarmSetTime(context: Context): Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return  preferences.getLong(ALARM_SET_TIME_ID, 0)
        }

        fun setAlarmSetTime(context: Context, time: Long){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(ALARM_SET_TIME_ID, time)
            editor.apply()
        }
    }
}