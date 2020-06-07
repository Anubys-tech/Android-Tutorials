package com.anubys.example.sharedpreferencesdemo

import android.app.Application
import android.util.Log

/** @Author Created by Anubys on the 16.05.2020 */

val sharedPreferences: AppPreference by lazy {
    BaseApplication.appPreference!!
}

class BaseApplication : Application() {
    private val tag = BaseApplication::class.java.simpleName

    companion object {
        var appPreference: AppPreference? = null
    }

    override fun onCreate() {
        Log.v(tag,"TAG - BaseApplication - onCreate()")
        super.onCreate()

        appPreference = AppPreference(this)
    }
}
