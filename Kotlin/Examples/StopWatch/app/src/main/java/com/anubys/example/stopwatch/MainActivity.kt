package com.anubys.example.stopwatch

/** @Author Created by Anubys on the 11.10.2020 */

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val tagActivity = MainActivity::class.java.simpleName

    private var animation: Animation? = null
    private var pause: Long
        get() {
            return (0)
        }
        set(value) {}


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagActivity, "TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadAnimation()
        setListener()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun loadAnimation() {
        Log.d(tagActivity, "TAG - MainActivity - onCreate()")

        animation = AnimationUtils.loadAnimation(this, R.anim.rotation)
    }

    private fun setListener() {
        Log.d(tagActivity, "TAG - MainActivity - onCreate()")
        btn_start.setOnClickListener { start() }
        btn_stop.setOnClickListener { stop() }
    }

    private fun start() {
        Log.d(tagActivity, "TAG - MainActivity - start()")

        btn_start.visibility = View.INVISIBLE
        btn_stop.visibility = View.VISIBLE
        iv_time.startAnimation(animation)
        chronometer.format = "%s"
        chronometer.base = SystemClock.elapsedRealtime() - pause
        chronometer.start()
    }

    private fun stop() {
        Log.d(tagActivity, "TAG - MainActivity - stop()")

        btn_start.visibility = View.VISIBLE
        btn_stop.visibility = View.INVISIBLE
        iv_time.animation = animation
        chronometer.stop()
        chronometer.base = SystemClock.elapsedRealtime()
        pause = 0
        animation?.cancel()
    }
}
