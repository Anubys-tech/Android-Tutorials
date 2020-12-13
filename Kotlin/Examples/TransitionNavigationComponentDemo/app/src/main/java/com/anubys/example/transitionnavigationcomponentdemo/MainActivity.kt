package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName



    //* ************************************************* *
    //*                L I F E - C Y C L E                *
    //* ************************************************* *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
