package com.anubys.example.bottomnavigationwithdrawermenudemo.ui.gallery

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
import com.anubys.example.bottomnavigationwithdrawermenudemo.ui.home.HomeFragment


class GalleryFragment : Fragment() {
    private val tagFragment = GalleryFragment::class.java.simpleName

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagFragment, "TAG - GalleryFragment - onCreateView()")
        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return (root)
    }
}
