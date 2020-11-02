package com.anubys.exercises.chatbotapp.ui

/** @Author Created by Anubys on the 02.11.2020 */

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.anubys.exercises.chatbotapp.R


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName


    //* ************************************************* *
    //*                L I F E - C Y C L E               `*
    //* ************************************************* *
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Log.d(tag,"TAG - MainActivity - onCreateView()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
