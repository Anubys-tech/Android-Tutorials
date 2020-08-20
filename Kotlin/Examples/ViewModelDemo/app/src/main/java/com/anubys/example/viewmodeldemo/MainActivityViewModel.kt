package com.anubys.example.viewmodeldemo

/** @Author Created by Anubys on the 20.08.2020 */

import android.util.Log

import androidx.lifecycle.ViewModel


class MainActivityViewModel constructor(_text: String) : ViewModel() {
    private val tag = MainActivityViewModel::class.java.simpleName

    var number: Int = 0
    var text: String = _text

    init {
        Log.d(tag,"TAG - MainActivityViewModel - init() -> $text")
    }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCleared() {
        Log.d(tag,"TAG - MainActivityViewModel - onCleared()")
        super.onCleared()
    }

    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun addNumber() {
        Log.d(tag,"TAG - MainActivityViewModel - addNumber()")
        number++
    }
}
