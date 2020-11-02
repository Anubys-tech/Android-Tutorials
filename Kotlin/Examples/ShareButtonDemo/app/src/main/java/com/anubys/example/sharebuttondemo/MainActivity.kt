package com.anubys.example.sharebuttondemo

/** @Author Created by Anubys on the 22.10.2020 */

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity

import com.anubys.example.sharebuttondemo.R


class MainActivity : AppCompatActivity() {
    private val tagFragment = MainActivity::class.java.simpleName


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagFragment, "TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tagFragment, "TAG - MainActivity - setListener()")


    }
}
