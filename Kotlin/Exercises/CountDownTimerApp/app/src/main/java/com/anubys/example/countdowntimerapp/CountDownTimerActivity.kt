package com.anubys.example.countdowntimerapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity

import com.anubys.example.countdowntimerapp.util.NotificationUtil
import com.anubys.example.countdowntimerapp.util.PreferenceUtil


import java.util.*

/** @Author Created by Anubys on the 07.05.2020 */


class CountDownTimerActivity : AppCompatActivity() {
    private val tag = CountDownTimerActivity::class.java.simpleName

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds: Long = 0L
    private var timerState = TimerState.Stopped
    private var secondsRemaining: Long = 0L


    companion object {
        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long{
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, CountDownTimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PreferenceUtil.setAlarmSetTime(context, nowSeconds)
            return wakeUpTime
        }

        fun removeAlarm(context: Context){
            val intent = Intent(context, CountDownTimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
            PreferenceUtil.setAlarmSetTime(context,0)
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }


    //* ************************************************ *
    //*               E N U M - C L A S S                *
    //* ************************************************ *
    enum class TimerState {
        Stopped, Paused, Running
    }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(tag, "TAG - CountDownTimerActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.ic_timer)
        supportActionBar?.title = "     Timer"

        setListener()
    }

    override fun onResume() {
        Log.v(tag, "TAG - CountDownTimerActivity - onResume()")
        super.onResume()

        initTimer()
        removeAlarm(this)
        NotificationUtil.hideTimerNotification(this)
    }

    override fun onPause() {
        Log.v(tag, "TAG - CountDownTimerActivity - onPause()")
        super.onPause()

        if (timerState == TimerState.Running){
            timer.cancel()
            val wakeUpTime = setAlarm(this, nowSeconds, secondsRemaining)
            NotificationUtil.showTimerRunning(this, wakeUpTime)
        }
        else if (timerState == TimerState.Paused){
            NotificationUtil.showTimerPaused(this)
        }

        PreferenceUtil.setPreviousTimerLengthSeconds(this, timerLengthSeconds)
        PreferenceUtil.setSecondsRemaining(this, secondsRemaining)
        PreferenceUtil.setTimerState(this, timerState)
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.v(tag, "TAG - CountDownTimerActivity - setListener()")
        fab_play.setOnClickListener { playCountDownTimer() }
        fab_pause.setOnClickListener { pauseCountDownTimer() }
        fab_stop.setOnClickListener { stopCountDownTimer() }
    }

    private fun playCountDownTimer() {
        Log.v(tag, "TAG - CountDownTimerActivity - playCountDownTimer()")
        startTimer()
        timerState =  TimerState.Running
        updateButtons()
    }

    private fun pauseCountDownTimer() {
        Log.v(tag, "TAG - CountDownTimerActivity - pauseCountDownTimer()")
        timer.cancel()
        timerState = TimerState.Paused
        updateButtons()
    }

    private fun stopCountDownTimer() {
        Log.v(tag, "TAG - CountDownTimerActivity - stopCountDownTimer()")
        timer.cancel()
        onTimerFinished()
    }

    private fun initTimer() {
        Log.v(tag, "TAG - CountDownTimerActivity - initTimer()")
        timerState = PreferenceUtil.getTimerState(this)

        if (timerState == TimerState.Stopped) {
            setNewTimerLength() // Neue Zeit setzen
        } else {
            setPreviousTimerLength() // Aktuelle Zeit setzen
        }

        secondsRemaining = if (timerState == TimerState.Running || timerState == TimerState.Paused) {
            PreferenceUtil.getSecondsRemaining(this)
        } else {
            timerLengthSeconds
        }

        val alarmSetTime = PreferenceUtil.getAlarmSetTime(this)
        if (alarmSetTime > 0) {
            secondsRemaining -= nowSeconds - alarmSetTime
        }

        if (secondsRemaining <= 0) onTimerFinished()
        else if (timerState == TimerState.Running) startTimer()

        updateButtons()
        updateCountDownUI()
    }

    private fun onTimerFinished() {
        Log.v(tag, "TAG - CountDownTimerActivity - onTimerFinished()")
        timerState = TimerState.Stopped

        setNewTimerLength()

        progress_countdown.progress = 0
        PreferenceUtil.setSecondsRemaining(this, timerLengthSeconds)
        secondsRemaining = timerLengthSeconds

        updateButtons()
        updateCountDownUI()
    }

    private fun startTimer() {
        Log.v(tag, "TAG - CountDownTimerActivity - startTimer()")
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountDownUI()
            }
        }.start()
    }

    private fun setNewTimerLength() {
        Log.v(tag, "TAG - CountDownTimerActivity - setNewTimerLength()")
        val lengthInMinutes = PreferenceUtil.getTimerLength(this)
        timerLengthSeconds = (lengthInMinutes * 60L)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength() {
        Log.v(tag, "TAG - CountDownTimerActivity - setPreviousTimerLength()")
        timerLengthSeconds = PreferenceUtil.getPreviousTimerLengthSeconds(this)
        progress_countdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountDownUI() {
        Log.v(tag, "TAG - CountDownTimerActivity - updateCountdownUI()")
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        txt_countdown.text = "$minutesUntilFinished:${if (secondsStr.length == 2) secondsStr else "0" + secondsStr}"
        progress_countdown.progress = (timerLengthSeconds - secondsRemaining).toInt()
    }

    private fun updateButtons() {
        Log.v(tag, "TAG - CountDownTimerActivity - updateButtons()")
        when (timerState) {
            TimerState.Running -> {
                fab_play.isEnabled = false
                fab_pause.isEnabled = true
                fab_stop.isEnabled = true
            }
            TimerState.Stopped -> {
                fab_play.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = false
            }
            TimerState.Paused -> {
                fab_play.isEnabled = true
                fab_pause.isEnabled = false
                fab_stop.isEnabled = true
            }
        }
    }


    //* ************************************************ *
    //*                    M E N U                       *
    //* ************************************************ *
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
