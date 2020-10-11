package com.anubys.example.introscreenwithnavigationcomponentdemo

/** @Author Created by Anubys on the 19.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

import kotlinx.android.synthetic.main.fragment_second_screen.view.*


class SecondScreenFragment : Fragment() {
    private val tagFragment = SecondScreenFragment::class.java.simpleName

    private var viewPager: ViewPager2? = null


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagFragment, "TAG - SecondScreenFragment - onCreateView()")
        val view = inflater.inflate(R.layout.fragment_second_screen, container, false)

        viewPager = activity?.findViewById(R.id.view_pager_intro)

        setListener(view)
        return (view)
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener(view: View) {
        Log.d(tagFragment, "TAG - SecondScreenFragment - setListener()")
        view.btn_screen_second?.setOnClickListener { goToThirdFragment() }
    }

    private fun goToThirdFragment() {
        Log.d(tagFragment, "TAG - SecondScreenFragment - goToThirdFragment()")
        viewPager?.currentItem = 2
    }
}
