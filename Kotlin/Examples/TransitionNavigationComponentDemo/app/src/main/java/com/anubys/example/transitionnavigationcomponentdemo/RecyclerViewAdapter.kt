package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter (private val items: List<DummyItem>) : RecyclerView.Adapter<ViewHolder>() {
    private val tag = RecyclerViewAdapter::class.java.simpleName

    lateinit var listener: ItemClickListener


    //* ************************************************* *
    //*                L I F E - C Y C L E                *
    //* ************************************************* *
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(tag,"TAG - RecyclerViewAdapter - onCreateViewHolder()")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(tag,"TAG - RecyclerViewAdapter - onBindViewHolder()")

        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size
}

