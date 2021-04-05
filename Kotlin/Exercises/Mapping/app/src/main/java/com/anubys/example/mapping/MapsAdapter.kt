package com.anubys.example.mapping

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anubys.example.mapping.models.UserMap


class MapsAdapter(private val context: Context, private val userMaps: List<UserMap>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<MapsAdapter.ViewHolder>() {


    //TODO bining verwenden umbauen
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userMap = userMaps[position]
        holder.itemView.setOnClickListener{
            Log.i("TAG", "Position-Adapter: $position")
            onClickListener.onItemClick(position)
        }
        //TODO auslagern
        val tvTitle = holder.itemView.findViewById<TextView>(android.R.id.text1)
        tvTitle.text = userMap.title
    }

    override fun getItemCount(): Int = userMaps.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
