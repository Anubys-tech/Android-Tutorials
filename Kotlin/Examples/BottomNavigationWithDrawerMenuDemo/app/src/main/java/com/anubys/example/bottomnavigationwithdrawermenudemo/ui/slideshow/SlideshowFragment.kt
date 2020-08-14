package com.anubys.example.bottomnavigationwithdrawermenudemo.ui.slideshow

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


class SlideshowFragment : Fragment() {
    private val tagFragment = SlideshowFragment::class.java.simpleName

    private lateinit var slideshowViewModel: SlideshowViewModel


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagFragment, "TAG - SlideshowFragment - onCreateView()")
        slideshowViewModel = ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return (root)
    }
}
