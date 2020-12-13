package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.anubys.example.transitionnavigationcomponentdemo.databinding.FragmentRecyclerViewBinding


class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {
    private val tagFragment = RecyclerViewFragment::class.java.simpleName

    private var viewBinding: FragmentRecyclerViewBinding? = null
    private val columnCount = 2
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    //* ************************************************* *
    //*                L I F E - C Y C L E                *
    //* ************************************************* *
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tagFragment,"TAG - RecyclerViewFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        viewBinding = FragmentRecyclerViewBinding.bind(view)

        val activity = activity as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setListener()

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            layoutManager = GridLayoutManager(context, columnCount)
            adapter = recyclerViewAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(tagFragment,"TAG - RecyclerViewFragment - onOptionsItemSelected()")

        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }

        return (super.onOptionsItemSelected(item))
    }

    override fun onDestroyView() {
        Log.d(tagFragment,"TAG - RecyclerViewFragment - onDestroyView()")
        super.onDestroyView()

        viewBinding = null
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tagFragment, "TAG - RecyclerViewFragment - setListener()")

        recyclerViewAdapter = RecyclerViewAdapter(DummyContent.items)
        recyclerViewAdapter.listener = object : ItemClickListener {
            override fun onItemClickListener(item: DummyItem, imageView: ImageView) {
                val extras = FragmentNavigatorExtras(
                    imageView to item.id
                )
                val action = RecyclerViewFragmentDirections.actionRecyclerviewItemdetail(id = item.id)
                findNavController().navigate(action, extras)
            }
        }
    }
}
