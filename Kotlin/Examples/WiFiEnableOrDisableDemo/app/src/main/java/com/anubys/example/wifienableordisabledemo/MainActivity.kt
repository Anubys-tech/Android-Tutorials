package com.anubys.example.wifienableordisabledemo

/** @Author Created by Anubys on the 01.12.2020 */

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "TAG - MainActivity - create()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tag, "TAG - MainActivity - setListener()")

        /*
        * Ab Android 10 kann nicht mehr direkt die Wifi aktiviert/deaktiviert werden. Man kann nur
        * noch in die Settings verweisen und der Benutzer muss das Manuell machen.
        */
        btn_enable.setOnClickListener { enable() }
        btn_disable.setOnClickListener { disable() }
    }

    private fun enable() {
        Log.d(tag, "TAG - MainActivity - enable()")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
            startActivityForResult(panelIntent, 0)
        } else {
            // add appropriate permissions to AndroidManifest file (see https://stackoverflow.com/questions/3930990/android-how-to-enable-disable-wifi-or-internet-connection-programmatically/61289575)
            (applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.apply { isWifiEnabled = true }
        }

        tv_display.text = "WiFi ist an"
    }

    private fun disable() {
        Log.d(tag, "TAG - MainActivity - disable()")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
            startActivityForResult(panelIntent, 0)
        } else {
            // add appropriate permissions to AndroidManifest file (see https://stackoverflow.com/questions/3930990/android-how-to-enable-disable-wifi-or-internet-connection-programmatically/61289575)
            (applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.apply { isWifiEnabled = false }
        }

        tv_display.text = "WiFi ist aus"
    }
}
