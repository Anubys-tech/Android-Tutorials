package com.anubys.example.introscreenwithnavigationcomponentdemo

/** @Author Created by Anubys on the 19.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {
    private val tagFragment = HomeFragment::class.java.simpleName


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagFragment, "TAG - HomeFragment - onCreateView()")
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return (view)
    }
}
