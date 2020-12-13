package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.anubys.example.transitionnavigationcomponentdemo.databinding.FragmentStartBinding


class StartFragment : Fragment(R.layout.fragment_start) {
    private val tagFragment = StartFragment::class.java.simpleName

    private var viewBinding: FragmentStartBinding? = null


    //* ************************************************* *
    //*                L I F E - C Y C L E                *
    //* ************************************************* *
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tagFragment,"TAG - StartFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentStartBinding.bind(view)
        setListener()
    }

    override fun onDestroyView() {
        Log.d(tagFragment,"TAG - StartFragment - onDestroyView()")
        super.onDestroyView()

        viewBinding = null
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tagFragment,"TAG - StartFragment - setListener()")

        viewBinding?.btnSingleItem?.setOnClickListener { findNavController().navigate(R.id.action_start_parent) }
        viewBinding?.btnRecyclerview?.setOnClickListener { findNavController().navigate(R.id.action_start_recyclerview) }
    }
}
