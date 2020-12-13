package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController

import com.anubys.example.transitionnavigationcomponentdemo.databinding.FragmentParentBinding


class ParentFragment : Fragment(R.layout.fragment_parent) {
    private val tagFragment = ParentFragment::class.java.simpleName

    private var viewBinding: FragmentParentBinding? = null


    //* ************************************************* *
    //*                L I F E - C Y C L E                *
    //* ************************************************* *
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - ParentFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        viewBinding = FragmentParentBinding.bind(view)

        val activity = activity as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(tagFragment,"TAG - StartFragment - onOptionsItemSelected()")

        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }

        return (super.onOptionsItemSelected(item))
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
        Log.d(tagFragment, "TAG - ParentFragment - setListener()")

        viewBinding?.cardview?.setOnClickListener{
            val extras = FragmentNavigatorExtras(
                viewBinding!!.tvImage to "imageView"
            )

            findNavController().navigate(R.id.action_parent_details, null, null, extras)
        }
    }
}
