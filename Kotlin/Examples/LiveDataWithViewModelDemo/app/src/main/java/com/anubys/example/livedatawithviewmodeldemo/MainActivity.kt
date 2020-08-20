package com.anubys.example.livedatawithviewmodeldemo

/** @Author Created by Anubys on the 20.08.2020 */

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.anubys.example.viewmodeldemo.MainActivityViewModel
import com.anubys.example.viewmodeldemo.MainActivityViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelFactory = MainActivityViewModelFactory("Das ist ein Demo der MainActivityViewModelFactory!")
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        viewModel.getSeconds().observe(this, {
            tv_display.text = it.toString()
        })
        viewModel.isCountDownTimerFinish().observe(this, {
            if (it) {
                Toast.makeText(this, "Finished!", Toast.LENGTH_LONG).show()
            }
        })


        setListener()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tag,"TAG - MainActivity - setListener()")
        btn_start?.setOnClickListener { start() }
        btn_stop?.setOnClickListener { stop() }
    }

    private fun start() {
        Log.d(tag, "TAG - MainActivity - start()")

        if (edt_input.text?.isEmpty()!! || edt_input.text?.length!! < 4) {
            Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.timerValue.value = edt_input.text.toString().toLong()
            viewModel.startTimer()
        }
    }

    private fun stop() {
        Log.d(tag, "TAG - MainActivity - stop()")
        tv_display.text = "0"
        viewModel.stopTimer()
    }
}