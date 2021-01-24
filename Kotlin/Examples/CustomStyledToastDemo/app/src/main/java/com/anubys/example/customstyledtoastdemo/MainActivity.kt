package com.anubys.example.customstyledtoastdemo

/** @Author Created by Anubys on the 24.01.2021 */

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.anubys.example.customstyledtoastdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val tag = MainActivity::class.java.simpleName

    private lateinit var binding: ActivityMainBinding


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "TAG - MainActivity - create()")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tag, "TAG - MainActivity - setListener()")

        binding.btnShowToast.setOnClickListener { showToast() }
    }

    private fun showToast() {
        Log.d(tag, "TAG - MainActivity - showToast()")

        Toast(this).showCustomToast(
            "This is a custom Toast!",
            this
        )
    }
}
