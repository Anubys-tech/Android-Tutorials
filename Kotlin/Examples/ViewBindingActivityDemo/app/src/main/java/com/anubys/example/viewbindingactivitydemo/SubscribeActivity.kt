package com.anubys.example.viewbindingactivitydemo

/** @Author Created by Anubys on the 07.08.2020 */

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.anubys.example.viewbindingactivitydemo.databinding.ActivitySubscribeBinding


class SubscribeActivity : AppCompatActivity() {
    private val tag = SubscribeActivity::class.java.simpleName


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(tag,"TAG - SubscribeActivity - onCreate()")
        super.onCreate(savedInstanceState)
        val viewBinding = ActivitySubscribeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}
