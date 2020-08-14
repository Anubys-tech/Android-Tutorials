package com.anubys.example.bottomnavigationwithdrawermenudemo.ui.home

/** @Author Created by Anubys on the 14.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.anubys.example.bottomnavigationwithdrawermenudemo.R


class HomeFragment : Fragment() {
    private val tagFragment = HomeFragment::class.java.simpleName

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagFragment, "TAG - HomeFragment - onCreateView()")
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return (root)
    }
}
