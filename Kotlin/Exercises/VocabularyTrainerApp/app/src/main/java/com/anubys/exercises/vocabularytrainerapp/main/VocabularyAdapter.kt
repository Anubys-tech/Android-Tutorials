package com.anubys.exercises.vocabularytrainerapp.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anubys.exercises.vocabularytrainerapp.R

class VocabularyAdapter(var list: ArrayList<String>) : RecyclerView.Adapter<VocabularyAdapter.ViewHolder>() {

    private val statusDrawable = arrayOf(R.drawable.ic_open, R.drawable.ic_work, R.drawable.ic_done)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_item_vocabulary, parent, false)
        return (ViewHolder(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return (list.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvWord: TextView = itemView.findViewById(R.id.tv_word)
        var tvSubTitle: TextView = itemView.findViewById(R.id.tv_subtitle)
        var ivStatus: ImageView = itemView.findViewById(R.id.iv_status)
    }
}
