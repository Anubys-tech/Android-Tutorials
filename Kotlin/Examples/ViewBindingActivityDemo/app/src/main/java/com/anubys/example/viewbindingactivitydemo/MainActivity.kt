package com.anubys.example.viewbindingactivitydemo

/** @Author Created by Anubys on the 07.08.2020 */

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import com.anubys.example.viewbindingactivitydemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    private var viewBinding: ActivityMainBinding? = null


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        setListener(viewBinding)
    }

    override fun onDestroy() {
        Log.v(tag,"TAG - MainActivity - onCreate()")
        super.onDestroy()
        viewBinding = null
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener(viewBinding: ActivityMainBinding?) {
        Log.v(tag,"TAG - MainActivity - setListener()")
        viewBinding?.btnClickMe?.setOnClickListener{ clickMe(viewBinding) }
        viewBinding?.btnToToSubscribe?.setOnClickListener { gotToSubscribe() }
    }

    private fun clickMe(viewBinding: ActivityMainBinding?) {
        Log.v(tag,"TAG - MainActivity - clickMe()")
        Toast.makeText(applicationContext,"Clicked me", Toast.LENGTH_LONG).show()
        viewBinding?.tvShow?.text = viewBinding?.edtName?.text

    }

    private fun gotToSubscribe() {
        Log.v(tag,"TAG - MainActivity - gotToSubscribe()")
        Toast.makeText(applicationContext,"To to Subscribe", Toast.LENGTH_LONG).show()
        intent = Intent(applicationContext, SubscribeActivity::class.java)
        startActivity(intent)
    }
}
