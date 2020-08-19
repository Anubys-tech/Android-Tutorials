package com.anubys.example.introscreenwithnavigationcomponentdemo

/** @Author Created by Anubys on the 19.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.fragment_intro_pager_fragament.view.*


class IntroPagerFragment : Fragment() {
    private val tagFragment = IntroPagerFragment::class.java.simpleName

    //Das kann auch direkt bei IntroPagerAdapter gemacht werden
    private val fragmentList = arrayListOf(
        FirstScreenFragment(),
        SecondScreenFragment(),
        ThirdScreenFragment()
    )

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagFragment, "TAG - IntroPagerFragment - onCreateView()")
        val view = inflater.inflate(R.layout.fragment_intro_pager_fragament, container, false)

        val adapter = IntroPagerAdapter(requireActivity().supportFragmentManager, lifecycle, fragmentList)
        view.view_pager_intro.adapter = adapter

        return (view)
    }
}
