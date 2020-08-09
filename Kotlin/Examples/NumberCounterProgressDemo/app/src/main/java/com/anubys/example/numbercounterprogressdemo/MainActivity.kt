package com.anubys.example.numbercounterprogressdemo

/** @Author Created by Anubys on the 09.08.2020 */

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.anubys.example.numbercounterprogressdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    private var binding: ActivityMainBinding? = null


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        startAnimationCounter(0, 100)
    }

    override fun onDestroy() {
        Log.v(tag,"TAG - MainActivity - onDestroy()")
        super.onDestroy()
        binding = null
    }


    //**************************************************
    //*         H E L P E R  -  M E T H O D S          *
    //**************************************************
    private fun startAnimationCounter(start: Int, end: Int) {
        Log.v(tag,"TAG - MainActivity - startAnimationCounter()")
        val valueAnimator: ValueAnimator = ValueAnimator.ofInt(start, end)
        valueAnimator.duration = 5000
        /*
        valueAnimator.addUpdateListener { animation ->
            binding?.tvCounter?.text = animation?.animatedValue.toString()
            binding?.progressbar?.progress = animation?.animatedValue.toString().toInt()
        }
        */
        valueAnimator.addUpdateListener ( OnAnimationUpdateListener() )
        valueAnimator.start()
    }


    //* ************************************************ *
    //*                 C L A S S E S                    *
    //* ************************************************ *
    private inner class OnAnimationUpdateListener : ValueAnimator.AnimatorUpdateListener {
        private val tag = OnAnimationUpdateListener::class.java.simpleName

        override fun onAnimationUpdate(animation: ValueAnimator?) {
            Log.v(tag,"TAG - OnAnimationUpdateListener - onAnimationUpdate()")
            binding?.tvCounter?.text = animation?.animatedValue.toString()
            binding?.progressbar?.progress = animation?.animatedValue.toString().toInt()
        }
    }
}
