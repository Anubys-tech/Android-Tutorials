package com.anubys.example.sharedpreferencesdemo

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

/** @Author Created by Anubys on the 11.05.2020
 *  Update: 16.05.2020
 *  */

// TODO Kolin-Like machen mit get() und set(), falls es geht
class AppPreference constructor(context: Context) {
    private val tag = AppPreference::class.java.simpleName

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(Constants.SETTINGS_KEY, Context.MODE_PRIVATE)


    //* ************************************************ *
    //*                    N A M E                       *
    //* ************************************************ *
    var name: String?
        get() = sharedPreferences.getString(Constants.NAME_KEY, "Unknown")
        set(value) = sharedPreferences.edit().putString(Constants.NAME_KEY, value).apply()

    fun removeName() {
        Log.v(tag, "TAG - AppPreference - removeName()")
        sharedPreferences.edit()?.remove(Constants.NAME_KEY)?.apply()
    }


    //* ************************************************ *
    //*                   E M A I L                      *
    //* ************************************************ *
    var email: String?
        get() = sharedPreferences.getString(Constants.EMAIL_KEY, "Unknown")
        set(value) = sharedPreferences.edit().putString(Constants.EMAIL_KEY, value).apply()

    fun removeEmail() {
        Log.v(tag, "TAG - AppPreference - removeEmail()")
        sharedPreferences.edit()?.remove(Constants.EMAIL_KEY)?.apply()
    }
}
