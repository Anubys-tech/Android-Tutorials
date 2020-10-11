package com.anubys.example.introscreenwithnavigationcomponentdemo

/** @Author Created by Anubys on the 19.08.2020 */

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.fragment_third_screen.view.*


class ThirdScreenFragment : Fragment() {
    private val tagFragment = ThirdScreenFragment::class.java.simpleName


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagFragment, "TAG - ThirdScreenFragment - onCreateView()")
        val view = inflater.inflate(R.layout.fragment_third_screen, container, false)

        setListener(view)
        return (view)
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener(view: View) {
        Log.d(tagFragment, "TAG - ThirdScreenFragment - setListener()")
        view.btn_finish?.setOnClickListener { goToHomeFragment() }
    }

    private fun goToHomeFragment() {
        Log.d(tagFragment, "TAG - ThirdScreenFragment - goToHomeFragment()")
        findNavController().navigate(R.id.action_introPager_to_home)
        introPagerFinish()
    }

    private fun introPagerFinish() {
        Log.d(tagFragment, "TAG - ThirdScreenFragment - introPagerFinish()")
        val sharedPreferences =  requireActivity().getSharedPreferences("introFinish", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}
