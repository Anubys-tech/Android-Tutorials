package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.anubys.example.transitionnavigationcomponentdemo.databinding.FragmentDetailBinding


class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val tagFragment = DetailFragment::class.java.simpleName

    private var viewBinding: FragmentDetailBinding? = null


    //* ************************************************* *
    //*                L I F E - C Y C L E                *
    //* ************************************************* *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagFragment,"TAG - DetailFragment - onCreate()")
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tagFragment,"TAG - DetailFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentDetailBinding.bind(view)

        val activity = activity as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(tagFragment,"TAG - DetailFragment - onOptionsItemSelected()")

        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }

        return (super.onOptionsItemSelected(item))
    }

    override fun onDestroyView() {
        Log.d(tagFragment,"TAG - DetailFragment - onDestroyView()")
        super.onDestroyView()

        viewBinding = null
    }
}
