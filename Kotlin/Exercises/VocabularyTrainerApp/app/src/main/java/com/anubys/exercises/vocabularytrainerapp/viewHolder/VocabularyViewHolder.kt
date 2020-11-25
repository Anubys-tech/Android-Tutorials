package com.anubys.exercises.vocabularytrainerapp.viewHolder

/** @Author Created by Anubys on the 22.11.2020 */

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import com.anubys.exercises.vocabularytrainerapp.R
import com.anubys.exercises.vocabularytrainerapp.interfaces.OnItemClickListener
import com.anubys.exercises.vocabularytrainerapp.interfaces.OnItemLongClickListener


class VocabularyViewHolder(itemView: View, onItemClickListener: OnItemClickListener, onItemLongClickListener: OnItemLongClickListener) : RecyclerView.ViewHolder(itemView) {
    private val tagViewHolder = VocabularyViewHolder::class.java.simpleName

    var tvForeignWord: TextView = itemView.findViewById(R.id.tv_foreign_word)
    var tvNativeWord: TextView = itemView.findViewById(R.id.tv_native_word)
    var ivStatus: ImageView = itemView.findViewById(R.id.iv_status)


    //* *************************************************** *
    //*                       I N I T                       *
    //* *************************************************** *
    init {
        Log.d(tagViewHolder, "TAG - VocabularyViewHolder - init()")

        itemView.setOnClickListener {
            Log.d(tagViewHolder, "TAG - VocabularyViewHolder - setOnClickListener()")

            onItemClickListener.setOnItemClickListener(adapterPosition)
        }

        itemView.setOnLongClickListener {
            Log.d(tagViewHolder, "TAG - VocabularyViewHolder - setOnLongClickListener()")

            onItemLongClickListener.setOnItemLongClickListener(adapterPosition)
            true
        }
    }
}
