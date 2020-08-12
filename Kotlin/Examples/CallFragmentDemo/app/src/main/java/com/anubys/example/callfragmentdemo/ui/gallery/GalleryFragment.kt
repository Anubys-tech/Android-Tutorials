package com.anubys.example.callfragmentdemo.ui.gallery

/** @Author Created by Anubys on the 10.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.anubys.example.callfragmentdemo.BlackFragment
import com.anubys.example.callfragmentdemo.R
import com.anubys.example.callfragmentdemo.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment() {
    private val tagFragment = GalleryFragment::class.java.simpleName

    private lateinit var galleryViewModel: GalleryViewModel
    private var binding: FragmentGalleryBinding? = null

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v(tagFragment,"TAG - GalleryFragment - onCreateView()")
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        return (binding?.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v(tagFragment,"TAG - GalleryFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    override fun onDestroyView() {
        Log.v(tagFragment,"TAG - GalleryFragment - onDestroyView()")
        super.onDestroyView()
        binding = null
    }

    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.v(tagFragment, "TAG - GalleryFragment - setListener()")
        binding?.btnGotoBlack?.setOnClickListener { goToFragment() }
    }

    private fun goToFragment() {
        Log.v(tagFragment, "TAG - GalleryFragment - goToFragment()")
        val fragment: Fragment = BlackFragment()
        val fragmentManager: FragmentManager? = activity?.supportFragmentManager
        val fragmentTransaction: FragmentTransaction? = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}
