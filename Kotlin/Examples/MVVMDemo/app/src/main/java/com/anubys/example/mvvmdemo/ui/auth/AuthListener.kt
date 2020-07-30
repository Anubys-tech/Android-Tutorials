package com.anubys.example.mvvmdemo.ui.auth

/** @Author Created by Anubys on the 21.07.2020 */


interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}
