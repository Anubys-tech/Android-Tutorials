package com.anubys.exercises.chatbotapp.utils

/** @Author Created by Anubys on the 02.11.2020 */

import android.util.Log

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


/**
 * DE: Ermittelt die aktuelle Uhrzeit und gibt diese zurück.
 * EN: Determines the current time and returns it.
 */
object Time {
    private val tag = Time::class.java.simpleName

    fun timeStamp(): String {
        Log.d(tag,"TAG - Time - timeStamp()")
        val timeStamp = Timestamp(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        val time = simpleDateFormat.format(Date(timeStamp.time))

        return (time.toString())
    }
}
