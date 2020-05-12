package com.anubys.example.sharedpreferencesdemo

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/** @Autor Created by Anubys on the 11.05.2020 */

// TODO Kolin-Like machen mit get() und set(), falls es geht
class AppPreference constructor(context: Context) {
    private val tag = AppPreference::class.java.simpleName

    private val SETTINGS_KEY = "SETTINGS_KEY"
    private val NAME = "NAME"
    private val EMAIL = "EMAIL"

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(SETTINGS_KEY, Context.MODE_PRIVATE)
    }

    //* ************************************************ *
    //*                    S A V E                       *
    //* ************************************************ *
    fun saveName(name: String) {
        Log.i(tag, "saveName: $name")
        sharedPreferences?.edit()?.putString(NAME, name)?.apply()
    }

    fun saveEmail(email: String) {
        Log.i(tag, "saveName: $email")
        sharedPreferences?.edit()?.putString(EMAIL, email)?.apply()
    }

    fun loadName(): String {
        Log.i(tag, "loadEmail: ${sharedPreferences?.getString(EMAIL, "Unknown")!!}")
        return (sharedPreferences?.getString(NAME, "Unknown")!!)
    }

    fun loadEmail(): String {
        Log.i(tag, "loadEmail: ${sharedPreferences?.getString(EMAIL, "Unknown")!!}")
        return (sharedPreferences?.getString(EMAIL, "Unknown")!!)
    }

    fun deleteName() {
        Log.i(tag, "deleteName")
        sharedPreferences?.edit()?.remove(NAME)?.apply()
    }

    fun deleteEmail() {
        Log.i(tag, "deleteEmail")
        sharedPreferences?.edit()?.remove(EMAIL)?.apply()
    }
}
