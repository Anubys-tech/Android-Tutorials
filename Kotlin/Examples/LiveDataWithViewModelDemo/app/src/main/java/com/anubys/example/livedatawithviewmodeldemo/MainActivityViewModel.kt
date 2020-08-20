package com.anubys.example.viewmodeldemo

/** @Author Created by Anubys on the 20.08.2020 */

import android.os.CountDownTimer
import android.util.Log

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel constructor(text: String) : ViewModel() {
    private val tag = MainActivityViewModel::class.java.simpleName

    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()
    private var _text: String = text
    private var _finished = MutableLiveData<Boolean>()
    var timerValue = MutableLiveData<Long>()


    init {
        Log.d(tag,"TAG - MainActivityViewModel - init() -> $_text")
    }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCleared() {
        Log.d(tag,"TAG - MainActivityViewModel - onCleared()")
        super.onCleared()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun startTimer() {
        Log.d(tag,"TAG - MainActivityViewModel - startTimer()")
        timer = object : CountDownTimer(timerValue.value?.toLong()!!, 1000) {
            override fun onTick(p0: Long) {
                Log.d(tag,"TAG - MainActivityViewModel - onTick() -> ${_seconds.value}")
                val timeLeft = p0/1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                Log.d(tag,"TAG - MainActivityViewModel - onFinish()")
                _finished.value = true
            }
        }.start()
    }

    fun stopTimer() {
        Log.d(tag,"TAG - MainActivityViewModel - stopTimer()")
        timer.cancel()
    }

    fun getSeconds() : LiveData<Int> {
        Log.d(tag,"TAG - MainActivityViewModel - getSeconds()")
        return (_seconds)
    }

    fun isCountDownTimerFinish() : LiveData<Boolean> {
        Log.d(tag,"TAG - MainActivityViewModel - getSeconds()")
        return (_finished)
    }
}
