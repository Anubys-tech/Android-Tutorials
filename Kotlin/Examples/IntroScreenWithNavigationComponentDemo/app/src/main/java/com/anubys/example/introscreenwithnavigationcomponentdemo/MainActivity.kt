package com.anubys.example.introscreenwithnavigationcomponentdemo

/** @Author Created by Anubys on the 19.08.2020 */

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val tagActivity = MainActivity::class.java.simpleName


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagActivity, "TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}
