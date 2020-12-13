package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater

import com.anubys.example.transitionnavigationcomponentdemo.databinding.FragmentItemDetailBinding


class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {
    private val tagFragment = ItemDetailFragment::class.java.simpleName

    private var viewBinding: FragmentItemDetailBinding? = null
    private var dataBinding: FragmentItemDetailBinding? = null
    private val args: ItemDetailFragmentArgs by navArgs()


    //* ************************************************* *
    //*                L I F E - C Y C L E                *
    //* ************************************************* *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagFragment,"TAG - ItemDetailFragment - onCreate()")
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tagFragment,"TAG - ItemDetailFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        viewBinding = FragmentItemDetailBinding.bind(view)


        val activity = activity as AppCompatActivity
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initViewElements()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(tagFragment,"TAG - ItemDetailFragment - onOptionsItemSelected()")

        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }

        return (super.onOptionsItemSelected(item))
    }

    override fun onDestroyView() {
        Log.d(tagFragment,"TAG - ItemDetailFragment - onDestroyView()")
        super.onDestroyView()

        viewBinding = null
    }

    private fun initViewElements() {
        Log.d(tagFragment,"TAG - ItemDetailFragment - initViewElements()")

        viewBinding?.ivItemImage?.transitionName = args.id
        viewBinding?.tvItemName?.text = args.id
    }
}
