package com.anubys.example.sharedpreferencesdemo

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

/** @Author Created by Anubys on the 11.05.2020
 *  Update: 16.05.2020
 *  */


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(tag, "OnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.i(tag, "setListener")
        btn_save.setOnClickListener { save() }
        btn_load.setOnClickListener { load() }
        btn_remove.setOnClickListener { delete() }
    }

    private fun save() {
        Log.i(tag, "save")
        sharedPreferences.name = edt_name.text.toString()
        sharedPreferences.email = edt_email.text.toString()

        Toast.makeText(this, getString(R.string.txt_daten_erfolgreich_gespeichert_in_preferences), Toast.LENGTH_LONG).show()
    }

    private fun load() {
        Log.i(tag, "laod")
        tv_txt_name.text = sharedPreferences.name
        tv_txt_email.text = sharedPreferences.email

        Toast.makeText(this, getString(R.string.txt_daten_erfolgreich_geladen_aus_preferences), Toast.LENGTH_LONG).show()
    }

    private fun delete() {
        Log.i(tag, "delete")
        sharedPreferences.removeName()
        sharedPreferences.removeEmail()

        Toast.makeText(this, getString(R.string.txt_daten_erfolgreich_geloescht_aus_preferences), Toast.LENGTH_LONG).show()
    }
}
