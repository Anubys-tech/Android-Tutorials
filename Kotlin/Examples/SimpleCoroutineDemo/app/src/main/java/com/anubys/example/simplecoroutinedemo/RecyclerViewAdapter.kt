package com.anubys.example.simplecoroutinedemo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter(var content: ArrayList<Int>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val tag = RecyclerViewAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d(tag, "TAG - RecyclerViewAdapter - onCreateViewHolder()")
        return (ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(tag, "TAG - RecyclerViewAdapter - onBindViewHolder()")
        holder.text.text = "${content[position]}"
    }

    override fun getItemCount(): Int {
        Log.d(tag, "TAG - RecyclerViewAdapter - getItemCount()")
        return (content.size)
    }

    fun updateContent(content: ArrayList<Int>) {
        Log.d(tag, "TAG - RecyclerViewAdapter - updateContent()")
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.tv_item)
    }
}
