package com.anubys.example.viewbindingfragmentdemo.ui.gallery

/** @Author Created by Anubys on the 07.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment

import com.anubys.example.viewbindingfragmentdemo.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private val tag2 = GalleryFragment::class.java.simpleName

    private lateinit var galleryViewModel: GalleryViewModel
    private var bindingGallery: FragmentGalleryBinding? = null


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v(tag2,"TAG - GalleryFragment - onCreateView()")
        bindingGallery = FragmentGalleryBinding.inflate(layoutInflater)
        return (bindingGallery?.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v(tag2,"TAG - GalleryFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v(tag2,"TAG - GalleryFragment - onDestroyView()")
        super.onDestroy()
        bindingGallery = null
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.v(tag, "TAG - GalleryFragment - setListener()")
        bindingGallery?.btnGallery?.setOnClickListener { image() }
    }

    private fun image() {
        Log.v(tag, "TAG - GalleryFragment - image()")
        Toast.makeText(context, "Click image()", Toast.LENGTH_LONG).show()
    }
}
