package com.anubys.example.countdowntimerapp

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*

/** @Author Created by Anubys on the 09.05.2020 */


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Settings"
    }
}
