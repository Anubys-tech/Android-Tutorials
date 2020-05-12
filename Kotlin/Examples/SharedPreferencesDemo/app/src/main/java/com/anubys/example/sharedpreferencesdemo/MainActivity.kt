package com.anubys.example.sharedpreferencesdemo

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

/** @Autor Created by Anubys on the 11.05.2020 */


class MainActivity : AppCompatActivity() {
    private val tag = AppPreference::class.java.simpleName

    private var appPreferences: AppPreference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(tag, "OnCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appPreferences = AppPreference(this)

        setListener()
    }

    private fun setListener() {
        Log.i(tag, "setListener")
        btn_save.setOnClickListener { save() }
        btn_load.setOnClickListener { load() }
        btn_remove.setOnClickListener { delete() }
    }

    private fun save() {
        Log.i(tag, "save")
        val name: String = edt_name.text.toString()
        val email: String = edt_email.text.toString()

        appPreferences?.saveName(name)
        appPreferences?.saveEmail(email)

        Toast.makeText(this, getString(R.string.txt_daten_erfolgreich_gespeichert_in_preferences), Toast.LENGTH_LONG).show()
    }

    private fun load() {
        Log.i(tag, "laod")
        if (appPreferences?.loadName() != null) tv_txt_name.text = appPreferences?.loadName() else tv_txt_name.text = resources.getString(R.string.keine_daten_gefunden)
        if (appPreferences?.loadEmail() != null) tv_txt_email.text = appPreferences?.loadEmail() else tv_txt_email.text = resources.getString(R.string.keine_daten_gefunden)

        Toast.makeText(this, getString(R.string.txt_daten_erfolgreich_geladen_aus_preferences), Toast.LENGTH_LONG).show()
    }

    private fun delete() {
        Log.i(tag, "delete")
        appPreferences?.deleteName()
        appPreferences?.deleteEmail()

        Toast.makeText(this, getString(R.string.txt_daten_erfolgreich_geloescht_aus_preferences), Toast.LENGTH_LONG).show()
    }
}
