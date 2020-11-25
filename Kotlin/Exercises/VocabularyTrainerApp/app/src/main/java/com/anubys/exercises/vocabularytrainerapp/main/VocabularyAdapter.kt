package com.anubys.exercises.vocabularytrainerapp.main

/** @Author Created by Anubys on the 22.11.2020 */

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.anubys.exercises.vocabularytrainerapp.R
import com.anubys.exercises.vocabularytrainerapp.interfaces.OnItemClickListener
import com.anubys.exercises.vocabularytrainerapp.interfaces.OnItemLongClickListener
import com.anubys.exercises.vocabularytrainerapp.repository.database.Vocabulary
import com.anubys.exercises.vocabularytrainerapp.viewHolder.VocabularyViewHolder


class VocabularyAdapter(var list: ArrayList<Vocabulary>) : RecyclerView.Adapter<VocabularyViewHolder>() {
    private val tagAdapter = VocabularyAdapter::class.java.simpleName

    private val statusDrawable = arrayOf(R.drawable.ic_open, R.drawable.ic_work, R.drawable.ic_done)
    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var onItemLongClickListener: OnItemLongClickListener


    //* *************************************************** *
    //*                 L I F E - C Y C L E                 *
    //* *************************************************** *
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VocabularyViewHolder {
        Log.d(tagAdapter, "TAG - VocabularyAdapter - onCreateViewHolder()")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocabulary, parent, false)
        return (VocabularyViewHolder(view, onItemClickListener, onItemLongClickListener))
    }

    override fun onBindViewHolder(holder: VocabularyViewHolder, position: Int) {
        Log.d(tagAdapter, "TAG - VocabularyAdapter - onBindViewHolder()")

        val vocabulary = list[position]
        holder.tvForeignWord.text = vocabulary.foreignWord
        holder.tvNativeWord.text = vocabulary.nativeWord
        holder.ivStatus.setImageResource(statusDrawable[vocabulary.status])
    }

    override fun getItemCount(): Int {
        Log.d(tagAdapter, "TAG - VocabularyAdapter - getItemCount()")

        return (list.size)
    }


    //* *************************************************** *
    //*            H E L P E R  -  M E T H O D S            *
    //* *************************************************** *
    fun updateContent(list: ArrayList<Vocabulary>) {
        Log.d(tagAdapter, "TAG - VocabularyAdapter - updateContent()")

        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        Log.d(tagAdapter, "TAG - VocabularyAdapter - setOnItemClickListener()")

        this.onItemClickListener = onItemClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        Log.d(tagAdapter, "TAG - VocabularyAdapter - setOnItemLongClickListener()")

        this.onItemLongClickListener = onItemLongClickListener
    }
}
