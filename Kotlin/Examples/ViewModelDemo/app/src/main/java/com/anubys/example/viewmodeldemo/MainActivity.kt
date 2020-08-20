package com.anubys.example.viewmodeldemo

/** @Author Created by Anubys on the 20.08.2020 */


import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), LifecycleOwner {
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

        tv_display.text = viewModel.number.toString()

        btn_add.setOnClickListener {
            viewModel.addNumber()
            tv_display.text = viewModel.number.toString()
        }
    }
}
