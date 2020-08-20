package com.anubys.example.expandablefloatingactionbuttondemo

/** @Author Created by Anubys on the 20.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    private var clicked: Boolean = false

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim) }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tag,"TAG - MainActivity - setListener()")
        btn_add.setOnClickListener { add() }
        btn_edit.setOnClickListener { edit() }
        btn_user.setOnClickListener { user() }
    }

    private fun add() {
        Log.d(tag,"TAG - MainActivity - add()")
        Toast.makeText(this, "Add Button Clicked", Toast.LENGTH_SHORT).show()

        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        Log.d(tag,"TAG - MainActivity - setVisibility()")

        if (!clicked) {
            btn_user.visibility = View.VISIBLE
            btn_edit.visibility = View.VISIBLE
        } else {
            btn_user.visibility = View.INVISIBLE
            btn_edit.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        Log.d(tag,"TAG - MainActivity - setAnimation()")

        if (!clicked) {
            btn_user.startAnimation(fromBottom)
            btn_edit.startAnimation(fromBottom)
            btn_add.startAnimation(rotateOpen)
        } else {
            btn_user.startAnimation(toBottom)
            btn_edit.startAnimation(toBottom)
            btn_add.startAnimation(rotateClose)
        }
    }

    private fun setClickable(clicked: Boolean) {
        Log.d(tag,"TAG - MainActivity - setClickable()")

        if (!clicked) {
            btn_user.isClickable = true
            btn_edit.isClickable = true
        } else {
            btn_user.isClickable = false
            btn_edit.isClickable = false
        }
    }

    private fun edit() {
        Log.d(tag,"TAG - MainActivity - edit()")
        Toast.makeText(this, "Edit Button Clicked", Toast.LENGTH_SHORT).show()
    }

    private fun user() {
        Log.d(tag,"TAG - MainActivity - user()")
        Toast.makeText(this, "User Button Clicked", Toast.LENGTH_SHORT).show()
    }
}
