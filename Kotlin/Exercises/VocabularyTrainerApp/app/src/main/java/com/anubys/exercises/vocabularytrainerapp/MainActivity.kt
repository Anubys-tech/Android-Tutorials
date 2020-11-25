package com.anubys.exercises.vocabularytrainerapp

/** @Author Created by Anubys on the 22.11.2020 */

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.anubys.exercises.vocabularytrainerapp.dialog.DialogInput

import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private val tagActivity = MainActivity::class.java.simpleName


    //* *************************************************** *
    //*                 L I F E - C Y C L E                 *
    //* *************************************************** *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagActivity, "TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setListener()
    }


    //* *************************************************** *
    //*            H E L P E R  -  M E T H O D S            *
    //* *************************************************** *
    private fun setListener() {
        Log.d(tagActivity, "TAG - MainActivity - setListener()")

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val dialog = DialogInput()
            dialog.show(supportFragmentManager, "New Input")
        }
    }
}
