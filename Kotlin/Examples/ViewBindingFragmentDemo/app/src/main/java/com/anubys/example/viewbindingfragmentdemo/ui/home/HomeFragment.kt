package com.anubys.example.viewbindingfragmentdemo.ui.home

/** @Author Created by Anubys on the 07.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment

import com.anubys.example.viewbindingfragmentdemo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private val tag2 = HomeFragment::class.java.simpleName

    private lateinit var homeViewModel: HomeViewModel
    private var bindingHome: FragmentHomeBinding? = null

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v(tag2,"TAG - HomeFragment - onCreateView()")
        bindingHome = FragmentHomeBinding.inflate(layoutInflater)
        return (bindingHome?.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v(tag2,"TAG - HomeFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v(tag2,"TAG - HomeFragment - onDestroyView()")
        super.onDestroy()
        bindingHome = null
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.v(tag, "TAG - HomeFragment - setListener()")
        bindingHome?.btnLike?.setOnClickListener { like() }
    }

    private fun like() {
        Log.v(tag, "TAG - HomeFragment - like()")
        Toast.makeText(context, "Click Like()", Toast.LENGTH_LONG).show()
    }
}
